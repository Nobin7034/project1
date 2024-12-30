package com.example.organicfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Payment extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String fname,lname,cardno,cvv,exp;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        e1=(EditText) findViewById(R.id.firstname);
        e2=(EditText) findViewById(R.id.lastname);
        e3=(EditText) findViewById(R.id.cardno);
        e4=(EditText) findViewById(R.id.cvv);
        e5=(EditText) findViewById(R.id.exp);
        b1=(Button) findViewById(R.id.button2);

        t1=(TextView) findViewById(R.id.amount);

        t1.setText("Amount Payable"+Cart.pr);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                cardno=e3.getText().toString();
                cvv=e4.getText().toString();
                exp=e5.getText().toString();
                if(cardno.equalsIgnoreCase("")|| cardno.length()!=16)
                {
                    e3.setError("Card Number Must Have 16 numbers");
                    e3.setFocusable(true);
                }else if(cvv.equalsIgnoreCase("")|| cvv.length()!=3)
                {
                    e4.setError("Enter The Correct Cvv Number");
                    e4.setFocusable(true);
                }if(lname.equalsIgnoreCase(""))
                {
                    e2.setError("Enter First Name As Per card ");
                    e2.setFocusable(true);
                }if(fname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter First Name As Per Card");
                    e1.setFocusable(true);
                }
                else{
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse)Payment.this;
                    String q="/makepayment?id=" + Login.logid + "&orid=" + Cart.itemid ;
                    q = q.replace(" ", "%20");
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
                Toast.makeText(getApplicationContext(), "Payment Successfull...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Mybookings.class));
//                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
//                logid = ja1.getJSONObject(0).getString("login_id");
//                usertype = ja1.getJSONObject(0).getString("user_type");

//                SharedPreferences.Editor e = sh.edit();
//                e.putString("log_id", logid);
//                e.commit();

//                if(usertype.equals("user"))
//                {
//                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), Userhome.class));
//                }
//                else if(usertype.equals("delivery"))
//                {
//                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(),Deliveryhome.class));
//                }

            }
            else {
                Toast.makeText(getApplicationContext(), "Payment Failed Plese Try After Sometime", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Cart.class));
            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    } public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Cart.class);
        startActivity(b);


    }
}