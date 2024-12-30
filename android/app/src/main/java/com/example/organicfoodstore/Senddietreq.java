package com.example.organicfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Senddietreq extends AppCompatActivity implements JsonResponse,AdapterView.OnItemClickListener {

    EditText e1, e2, e3;
    Button b1;
    ListView l1;
    String height, wieght, age;

    //    String [] value,complaints,reply,date,sts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senddietreq);

        e1 = (EditText) findViewById(R.id.height);
        e2 = (EditText) findViewById(R.id.wieght);
        e3 = (EditText) findViewById(R.id.age);
        b1 = (Button) findViewById(R.id.login);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                height = e1.getText().toString();
                wieght = e2.getText().toString();
                age = e3.getText().toString();
                if (height.equalsIgnoreCase("")) {
                    e1.setError("Enter your height");
                    e1.setFocusable(true);
                } else if (wieght.equalsIgnoreCase("")) {
                    e2.setError("Enter your Weight");
                    e2.setFocusable(true);
                } else if (age.equalsIgnoreCase("")) {
                    e3.setError("Enter your Age");
                    e3.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Senddietreq.this;
                    String q = "/sendrequest?height=" + height + "&wieght=" + wieght + "&age=" + age + "&teamid=" + Userviewdieteam.dietid + "&logid=" + Login.logid;
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


                Toast.makeText(getApplicationContext(), "Request Send  Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Viewmyrequests.class));

//                else if(usertype.equals("delivery"))
//                {
//                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(),Deliveryhome.class));
//                }

            } else {
                Toast.makeText(getApplicationContext(), "Login failed invalid username and password", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}