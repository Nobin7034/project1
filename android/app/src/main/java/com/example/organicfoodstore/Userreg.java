package com.example.organicfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Userreg extends AppCompatActivity implements JsonResponse {

    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e0;
    Button b1;
    String fname,lname,place,phone,email,uname,passw,hname,pin,lmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userreg);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText) findViewById(R.id.phone);
        e5=(EditText) findViewById(R.id.email);
        e6=(EditText) findViewById(R.id.uname);
        e7=(EditText) findViewById(R.id.passw);
        e8=(EditText) findViewById(R.id.hname);
        e9=(EditText) findViewById(R.id.lmark);
        e0=(EditText) findViewById(R.id.pin);




        b1=(Button) findViewById(R.id.reg);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                uname=e6.getText().toString();
                passw=e7.getText().toString();
                hname=e8.getText().toString();
                lmark=e9.getText().toString();
                pin=e0.getText().toString();

                if(fname.equalsIgnoreCase("")|| !fname.matches("[a-zA-Z ]+"))
                {
                    e1.setError("Enter your firstname");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase("")|| !lname.matches("[a-zA-Z ]+"))
                {
                    e2.setError("Enter your lastname");
                    e2.setFocusable(true);
                }

                else if(place.equalsIgnoreCase("")|| !place.matches("[a-zA-Z ]+"))
                {
                    e3.setError("Enter your place");
                    e3.setFocusable(true);
                }
                else if(phone.equalsIgnoreCase("")|| phone.length()!=10)
                {
                    e4.setError("Enter your phone");
                    e4.setFocusable(true);
                }
                else if(email.equalsIgnoreCase("")|| !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"))
                {
                    e5.setError("Enter your email");
                    e5.setFocusable(true);
                }
                else if(uname.equalsIgnoreCase(""))
                {
                    e6.setError("Enter your username");
                    e6.setFocusable(true);
                }
                else if(passw.equalsIgnoreCase(""))
                {
                    e7.setError("Enter your password");
                    e7.setFocusable(true);
                }
                else if(hname.equalsIgnoreCase(""))
                {
                    e8.setError("Enter your House Name");
                    e8.setFocusable(true);
                }
                else if(lmark.equalsIgnoreCase(""))
                {
                    e9.setError("Enter your Proper Landmark");
                    e9.setFocusable(true);
                }
                else if(pin.equalsIgnoreCase("")|| pin.length()!=6)
                {
                    e0.setError("Enter your Pincode");
                    e0.setFocusable(true);
                }
                else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Userreg.this;
                    String q ="/userregister?fname="+fname+"&lname="+lname+"&place="+place+"&phone="+phone+"&email="+email+"&username="+uname+"&password="+passw+"&lmark="+lmark+"&hname="+hname+"&pin="+pin;
                    q = q.replace(" ","%20");
                    JR.execute(q);
                }
            }

        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));

            } else if (status.equalsIgnoreCase("duplicate")) {
                startActivity(new Intent(getApplicationContext(), Userreg.class));
                Toast.makeText(getApplicationContext(), "Username and Password already Exist...", Toast.LENGTH_LONG).show();

            } else {
                startActivity(new Intent(getApplicationContext(), Userreg.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Login.class);
        startActivity(b);

    }
}