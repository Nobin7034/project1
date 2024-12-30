from flask import *
from database import *
import uuid

	
public=Blueprint('public',__name__)

@public.route("/")
def home():
	
	return render_template("home.html")

@public.route('/login',methods=['post','get'])
def login():
	if "submit" in request.form:
		u=request.form['uname']
		p=request.form['pasd']
		q="select * from login where username='%s' and password='%s'"%(u,p)
		res=select(q)

		print(res)
		if res:
			session['lid']=res[0]['login_id']
			utype=res[0]['usertype']
			if utype=="admin":
				return redirect(url_for('admin.adminhome'))

			if utype=="store":
				q="select * from stores where login_id='%s'"%(session['lid'])
				res=select(q)
				if res:
					session['store_id']=res[0]['store_id']

					return redirect(url_for("store.storehome"))


			if utype=="diet":
				q="select * from diet where login_id='%s'"%(session['lid'])
				res=select(q)
				if res:
					session['diet_id']=res[0]['diet_id']

					return redirect(url_for("diet.diethome"))

		else:
			flash("invalid Username Or Passwords..")
			return redirect(url_for('public.login'))
			

	return render_template("login.html")

@public.route('/storereg',methods=['get','post'])
def storereg():
	data={}
	if 'add' in request.form:
		sname=request.form['sname']
		place=request.form['place']
		phone=request.form['phone']
		image=request.files['img']
		email=request.form['email']
		uname=request.form['uname']
		passw=request.form['passw']	
		pincode=request.form['pincode']
		bname=request.form['bname']
		path="static/uploads/"+str(uuid.uuid4())+image.filename
		image.save(path)
		q="select * from login where username='%s'"%(uname)
		res=select(q)
		if res:
			flash("Username Already Exists.....")
			return redirect(url_for('public.storereg'))
		else:
			q="insert into login values(null,'%s','%s','store')"%(uname,passw)
			id=insert(q)
			q="insert into stores values(null,'%s','%s','%s','%s','%s','%s','%s','%s','active')"%(id,sname,path,bname,place,pincode,phone,email)
			insert(q)
			flash("Registration Successfull")
			return redirect(url_for('public.login'))

	return render_template('storereg.html',data=data)



@public.route('/dietreg',methods=['get','post'])
def dietreg():
	data={}
	if 'add' in request.form:
		
		name=request.form['name']
		place=request.form['place']
		phone=request.form['phone']
		email=request.form['email']
		uname=request.form['uname']
		passw=request.form['passw']	
		q="select * from login where username='%s'"%(uname)
		res=select(q)
		if res:
			flash("Username Already Exists.....")
			return redirect(url_for('public.dietreg'))
		else:
			q="insert into login values(null,'%s','%s','diet')"%(uname,passw)
			id=insert(q)
			q="insert into diet values(null,'%s','%s','%s','%s','%s')"%(id,name,place,phone,email)
			insert(q)
			flash("Registration Successfull")
			return redirect(url_for('public.login'))

	return render_template('dietreg.html',data=data)