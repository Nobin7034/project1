from flask import *
from database import *
import uuid
# import qrcode


api=Blueprint('api',__name__)



@api.route('/userregister')
def userregister():
    
    data={}
 
  
    fname=request.args['fname']
    lname=request.args['lname']
    place=request.args['place']
    phone=request.args['phone']
    email=request.args['email']
    hname=request.args['hname']
    pin=request.args['pin']
    lmark=request.args['lmark']
    uname=request.args['username']
    passw=request.args['password']
    q="select * from login where username='%s'"%(uname)
    print(q)
    res=select(q)
    if res:
        data['status']='duplicate'
    else:
        q="insert into login values(null,'%s','%s','user')"%(uname,passw)
        print(q)
        id=insert(q)
        q="insert into users values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(id,fname,lname,hname,place,lmark,pin,phone,email)
        print(q)
        insert(q)
        data['status']='success'
    return str(data)

@api.route('/login')
def login():
    data={}
    username=request.args['username']
    password=request.args['password']

    q="select * from login where username='%s' and password='%s'"%(username,password)
    res=select(q)
    if res:
        data['status']='success'
        data['data']=res
    return str(data)


@api.route('/viewoutputs')
def viewoutputs():
    data={}
    q="select * from stores"
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    return str(data)


@api.route('/viewitems')
def viewitems():
    data={}
    id=request.args['id']
    q="select *,items.image as image from items inner join stores using(store_id) where store_id='%s'"%(id)
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    return str(data)


@api.route('/bookitems')
def bookitems():
    data={}
    itemid=request.args['id']
    price=request.args['price']
    quan=request.args['quantity']
    total=request.args['total']
    lid=request.args['lid']
    q="select * from order_master where store_id=(select store_id from items where item_id='%s') and user_id=(select user_id from users where login_id='%s') and status='pending'"%(itemid,lid)
    res=select(q)
    if res:
        ormid=res[0]['order_master_id']
        q="select * from order_child where order_master_id='%s' and item_id='%s'"%(ormid,itemid)
        res=select(q)
        if res:
            q="update order_master set total=total+'%s' where order_master_id='%s'"%(total,ormid)
            update(q)
            q="update order_child set quantity=quantity+'%s' where order_master_id='%s'"%(quan,ormid)
            update(q)
            data['status']='success'
        else:
            q="update order_master set total=total+'%s' where order_master_id='%s'"%(total,ormid)
            update(q)
            q="insert into order_child values(null,'%s','%s','%s','%s')"%(ormid,itemid,quan,price)
            insert(q)
            data['status']='success'
    else:
        q="insert into order_master values(null,(select store_id from items where item_id='%s'),(select user_id from users where login_id='%s'),'%s',now(),'pending')"%(itemid,lid,total)
        newid=insert(q)
        q="insert into order_child values(null,'%s','%s','%s','%s')"%(newid,itemid,quan,price)
        insert(q)
        data['status']='success'
        
    return str(data)



@api.route('/cart')
def cart():
    data={}
    id=request.args['id']
    q="SELECT * FROM order_master INNER JOIN order_child USING(order_master_id) INNER JOIN items USING(item_id) where user_id=(select user_id from users where login_id='%s') and status='pending'"%(id)    
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    return str(data)



@api.route('/makepayment')
def makepayment():
    data={}
    logid=request.args['id']
    orid=request.args['orid']
   
    q1="insert into payments values(null,'%s',curdate())"%(orid)
    insert(q1)
    q="update order_master set status='paid' where order_master_id='%s'"%(orid)
    update(q)
   
    data['status']='success'

    return str(data)
   


@api.route('/bookings')
def bookings():
    data={}
    id=request.args['id']
    q="SELECT * FROM order_master INNER JOIN order_child USING(order_master_id) INNER JOIN items USING(item_id) where user_id=(select user_id from users where login_id='%s') and status<>'pending'"%(id)    
    res=select(q)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    return str(data)


@api.route('/collected')
def collected():
    data={}
    id=request.args['id']
    q="update order_master set status='collected' where order_master_id='%s'"%(id)
    update(q)
    data['status']='success'
    return str(data)


@api.route('/addratings')
def addratings():
    data={}
    log_id=request.args['log_id']
    orid=request.args['orid']
    rating=request.args['rating']
    review=request.args['review']
    q="insert into rating values(null,(select user_id from users where login_id='%s'),'%s','%s','%s',now())"%(log_id,orid,rating,review)
    insert(q)
    q="update order_master set status='Reviewed' where order_master_id='%s'"%(orid)
    update(q)
    data['status']="success"
    data['method']='addratings'
    return str(data)



@api.route('/viewreply')
def viewreply():
    data={}
    id=request.args['id']
    q="select * from complaints inner join users using(user_id) where user_id=(select user_id from users where login_id='%s')"%(id)
    res=select(q)
    if res:
        data['status']='success'
        data['data']=res
    else:
        data['status']='failed'
    return str(data)

@api.route('/sendcomplaint')
def sendcomplaint():
    data={}
    id=request.args['id']
    complaint=request.args['complaint']
    q="insert into complaints values(null,(select user_id from users where login_id='%s'),'%s','pending',curdate())"%(id,complaint)
    insert(q)
    data['status']='success'
    return str(data)

@api.route('/viewdietteam')
def viewdietteam():
    data={}
    q="select * from diet "
    res=select(q)
    if res:
        data['status']='success'
        data['data']=res
    else:
        data['status']='failed'
    return str(data)

@api.route('/sendrequest')
def sendrequest():
    data={}
    logid=request.args['logid']
    teamid=request.args['teamid']
    height=request.args['height']
    wieght=request.args['wieght']
    age=request.args['age']
    q="insert into request values(null,'%s',(select user_id from users where login_id='%s'),now(),'pending')"%(teamid,logid)
    idd=insert(q)
    q="insert into details value(null,'%s','%s','%s','%s')"%(idd,age,wieght,height)
    insert(q)
    data['status']='success'
    return str(data)

@api.route('/viewrequeststatus')
def viewrequeststatus():
    data={}
    logid=request.args['logid']
    q="select * from request inner join diet using(diet_id) where user_id=(select user_id from users where login_id='%s')"%(logid)
    res=select(q)
    if res:
        data['status']='success'
        data['data']=res
    else:
        data['status']='failed'
    return str(data)


@api.route('/viewmyplans')
def viewmyplans():
    data={}
    reqid=request.args['reqid']
    q="select * from dietplan inner join request using(request_id) inner join diet using(diet_id) where request_id='%s'"%(reqid)
    print(q)
    res=select(q)
    if res:
        data['status']='success'
        data['data']=res
    else:
        data['status']='failed'
    return str(data)
