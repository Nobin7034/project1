from flask import *
from database import *

admin=Blueprint('admin',__name__)

@admin.route("/adminhome")
def adminhome():
	return render_template("adminhome.html")

@admin.route("/admin_manage_category",methods=['post','get'])
def admin_manage_category():
	data={}
	q="select * from categories"
	res=select(q)
	data['view']=res

	if "action" in request.args:
		action=request.args['action']
		cid=request.args['cid']
	else:
		action=None

	if action=="delete":
		q="delete from categories where category_id='%s'"%(cid)
		delete(q)
		
		return redirect(url_for('admin.admin_manage_category'))
	if action=="update":
		q="select * from categories where category_id='%s'"%(cid)
		res=select(q)
		data['up']=res
	if "update" in request.form:
		cat=request.form['cat']
		
		q="update categories set category_name='%s' where category_id='%s'"%(cat,cid)
		i=update(q)
		print(i)
		return redirect(url_for("admin.admin_manage_category"))

	if "submit" in request.form:
		cat=request.form['cat']
		s="insert into categories values(null,'%s')"%(cat)
		insert(s)
		return redirect(url_for("admin.admin_manage_category"))

	return render_template("admin_manage_category.html",data=data)


@admin.route("/admin_view_stores",methods=['post','get'])
def admin_view_stores():
	data={}
	q="select * from stores"
	res=select(q)
	data['view']=res

	if "action" in request.args:
		action=request.args['action']
		cid=request.args['cid']

	else:
		action=None

	if action=="accept":
		q="update stores set status='accepted' where store_id='%s'"%(cid)
		update(q)
		return redirect(url_for("admin.admin_view_stores"))

	if action=="reject":
		q="delete from stores where store_id='%s'"%(cid)
		update(q)
		return redirect(url_for("admin.admin_view_stores"))


	return render_template("admin_view_stores.html",data=data)


@admin.route("/admin_view_items",methods=['post','get'])
def admin_view_items():
	data={}
	# q="select * from items "
	q="SELECT *,items.image as itimage FROM items INNER JOIN `categories` USING(`category_id`) INNER JOIN `stores` USING(`store_id`)"
	res=select(q)
	data['view']=res
	return render_template("admin_view_items.html",data=data)

@admin.route("/admin_view_users",methods=['post','get'])
def admin_view_users():
	data={}
	q="select * from users"
	res=select(q)
	data['view']=res
	return render_template("admin_view_users.html",data=data)

@admin.route("/admin_view_complaints",methods=['post','get'])
def admin_view_complaints():
	data={}
	q="select * from complaints inner join users using(user_id)"
	res=select(q)
	data['view']=res
	return render_template("admin_view_complaints.html",data=data)


@admin.route("/admin_send_reply",methods=['post','get'])
def admin_send_reply():
	data={}
	cid=request.args['cid']
	if "submit" in request.form:
		reply=request.form['reply']
		s="update complaints set reply='%s' where complaint_id='%s'"%(reply,cid)
		update(s)
		return redirect(url_for('admin.admin_view_complaints'))
	return render_template("admin_send_reply.html",data=data)

@admin.route("/admin_view_ratings",methods=['post','get'])
def admin_view_ratings():
	data={}
	q="select * from rating"
	res=select(q)
	data['view']=res
	return render_template("admin_view_ratings.html",data=data)