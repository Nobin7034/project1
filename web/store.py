from flask import *
from database import *
import uuid

store=Blueprint('store',__name__)

@store.route("/storehome")
def storehome():
	return render_template("storehome.html")



@store.route('/storemanageitems',methods=['get','post'])
def storemanageitems():
    data={}

    q="select * from categories"
    data['cate']=select(q)


    q="select * from items inner join categories using (category_id) where store_id='%s'"%(session['store_id'])
    data['viewstations']=select(q)

    if 'add' in request.form:
        iname=request.form['iname']
        desc=request.form['desc']
        stock=request.form['stock']
        price=request.form['price']
        cat=request.form['cat']
        image=request.files['img']
        path="static/uploads/"+str(uuid.uuid4())+image.filename
        image.save(path)
        q="select * from items where item_name='realythishappens'"
        res=select(q)
        if res:
            flash("Username Already Exists.....")
            return redirect(url_for('store.storemanageitems'))
        else:
            q="insert into items values(null,'%s','%s','%s','%s','%s','%s','%s')"%(session['store_id'],cat,iname,desc,path,stock,price)
            insert(q)
            flash("Item Added Successfull")
            return redirect(url_for('store.storemanageitems'))
        
    if 'action' in request.args:
        action=request.args['action']
        did=request.args['id']
    
    else:
        action=None

    if action=='update':
        q="select * from items inner join categories using(category_id) where item_id='%s'"%(did)
        data['upstation']=select(q)
    
    if 'edit' in request.form:
        iname=request.form['iname']
        desc=request.form['desc']
        stock=request.form['stock']
        price=request.form['price']
        cat=request.form['cat']

        q="update items set item_name='%s',description='%s',stock=stock+'%s',price='%s',category_id='%s' where item_id='%s'"%(iname,desc,stock,price,cat,did)
        update(q)
        flash("Successfully Saved......")
        return redirect(url_for('store.storemanageitems'))
    
    if action=="delete":
        q="delete from items where item_id='%s'"%(did)
        delete(q)
        flash("Successfully Deleted......")
        return redirect(url_for('store.storemanageitems'))


    return render_template('storemanageitem.html',data=data)



@store.route("/storeviewbookings",methods=['post','get'])
def storeviewbookings():
	data={}
	q="SELECT * FROM order_child INNER JOIN order_master USING(order_master_id) INNER JOIN users USING (user_id) INNER JOIN items USING (item_id) WHERE order_master.store_id='%s'"%(session['store_id'])
	res=select(q)
	data['view']=res

	if "action" in request.args:
		action=request.args['action']
		id=request.args['id']

	else:
		action=None

	if action=="delivered":
		q="update order_master set status='delivered' where order_master_id='%s'"%(id)
		update(q)
		return redirect(url_for("store.storeviewbookings"))

	# if action=="reject":
	# 	q="update stores set status='rejected' where store_id='%s'"%(id)
	# 	update(q)
	# 	return redirect(url_for("admin.admin_view_stores"))


	return render_template("storeviewbookings.html",data=data)



@store.route("/storeviewpayments",methods=['post','get'])
def storeviewpayments():
    data={}
    id=request.args['id']
    q="SELECT * FROM order_child INNER JOIN order_master USING(order_master_id) INNER JOIN users USING (user_id) INNER JOIN items USING (item_id) inner join payments using(order_master_id) WHERE  order_master_id='%s'"%(id)
    res=select(q)
    data['view']=res

    return render_template("storeviewpayments.html",data=data)

@store.route("/storeviewratings",methods=['post','get'])
def storeviewratings():
    data={}
    id=request.args['id']
    q="SELECT * FROM order_child INNER JOIN order_master USING(order_master_id) INNER JOIN users USING (user_id) INNER JOIN items USING (item_id) inner join rating using(order_master_id) WHERE  order_master_id='%s'"%(id)
    res=select(q)
    data['view']=res

    return render_template("storeviewratings.html",data=data)