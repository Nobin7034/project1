from flask import Flask 
from public import public
from admin import admin
from store import store
from diet import diet
from api import api

app=Flask(__name__)
app.secret_key="hai"
app.register_blueprint(public)
app.register_blueprint(admin,url_prefix="/admin")
app.register_blueprint(store,url_prefix="/store")
app.register_blueprint(diet,url_prefix="/diet")
app.register_blueprint(api,url_prefix="/api")
app.run(debug=True,port=5007,host="0.0.0.0")