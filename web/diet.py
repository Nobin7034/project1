from flask import *
from database import *
import uuid

diet=Blueprint('diet',__name__)

@diet.route("/diethome")
def diethome():
	return render_template("diethome.html")

@diet.route("/diet_view_request",methods=['post','get'])
def diet_view_request():
	data={}

	q="SELECT * FROM request inner join users using(user_id) where diet_id='%s'"%(session['diet_id'])
	res=select(q)
	data['view']=res
	if "action" in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=="accept":
		q="update request set status='Accepted' where request_id='%s'"%(id)
		update(q)
		flash("accepted successfully")
		return redirect(url_for('diet.diet_view_request'))

	if action=="reject":
		q="update request set status='reject' where request_id='%s'"%(id)
		update(q)
		flash("rejected successfully")
		return redirect(url_for('diet.diet_view_request'))

	return render_template("diet_view_request.html",data=data)


@diet.route("/diet_add_plan",methods=['post','get'])
def diet_add_plan():
	data={}
	id=request.args['id']
	q="select * from dietplan where request_id='%s'"%(id)
	res=select(q)
	data['view']=res

	if "submit" in request.form:
		
		plan=request.form['plan']
		s="insert into dietplan values(null,'%s','%s')"%(id,plan)
		insert(s)
		return redirect(url_for("diet.diet_add_plan",id=id))

	if "action" in request.args:
		action=request.args['action']
		ids=request.args['id']
	else:
		action=None

	if action=="delete":
		q="delete from dietplan where diet_plan_id='%s'"%(id)
		delete(q)
		
		return redirect(url_for('diet.diet_view_request',id=id))



	

	return render_template("diet_add_plan.html",data=data)


@diet.route("/diet_view_details",methods=['post','get'])
def diet_view_details():
	data={}
	id=request.args['id']
	q="SELECT * FROM details  where request_id='%s'"%(id)
	res=select(q)
	data['view']=res
	return render_template("diet_view_details.html",data=data)