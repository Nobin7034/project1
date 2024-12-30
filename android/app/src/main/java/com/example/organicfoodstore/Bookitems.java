package com.example.organicfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Bookitems extends AppCompatActivity implements JsonResponse {
EditText e1,e2,e3;
String num1,num2,quan,price,total;
Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookitems);

        e1=(EditText) findViewById(R.id.quan);
        e2=(EditText) findViewById(R.id.price);
        e3=(EditText) findViewById(R.id.total);
        b1=(Button) findViewById(R.id.book);
        e2.setText(Viewitems.pr);


        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                num1=e1.getText().toString();
                num2=e2.getText().toString();
                if (num1.equals("")){

                    e3.setText("");
                }
                else{

                int num3=Integer.parseInt(num1) * Integer.parseInt(num2);

                e3.setText(num3+"");
            }

                }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quan=e1.getText().toString();
                price=e2.getText().toString();
                total=e3.getText().toString();


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Bookitems.this;
                String q = "/bookitems?id="+Viewitems.itemid+"&price="+price+"&quantity="+quan+"&total="+total+"&lid="+Login.logid;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {


                    Toast.makeText(getApplicationContext(),"Puchase Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Cart.class));

//                else if(usertype.equals("delivery"))
//                {
//                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(),Deliveryhome.class));
//                }

            }
            else {
                Toast.makeText(getApplicationContext(), "Purchase Failed", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    } public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Viewstores.class);
        startActivity(b);

    }
}