package com.example.organicfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Rateandreview extends AppCompatActivity implements JsonResponse {
    RatingBar r1;
    Button b1;
    TextView e1;
    String rate, status ,text;
    EditText e2;
    float rats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateandreview);

        r1 = (RatingBar) findViewById(R.id.rb);
        b1 = (Button) findViewById(R.id.btrate);
        e1 = (TextView) findViewById(R.id.etrate);
        e2 = (EditText) findViewById(R.id.uname);


//        JsonReq JR = new JsonReq();
//        JR.json_response = (JsonResponse) Rateandreview.this;
//        String q = "/viewrating?log_id=" +Login.logid+"&cid="+Viewcollage.col_id;
//        JR.execute(q);
//        Log.d("pearl", q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text=e2.getText().toString();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Rateandreview.this;
                String q = "/addratings?log_id=" +Login.logid+"&orid="+Mybookings.itemid+"&rating=" + rats+"&review="+text;
                JR.execute(q);
                Log.d("pearl", q);

            }
        });
        r1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                e1.setText("Your Rating :\t" + rating);
                rats = rating;
//                rat = e1.getText().toString();
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");
            Log.d("pearl", method);

            if (method.equalsIgnoreCase("addratings")) {
                String status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "tanks for the review", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Mybookings.class));
                }
            } else if (method.equalsIgnoreCase("viewratings")) {

                status = jo.getString("status");
                Log.d("pearl", status);
                String data = jo.getString("data");
                Log.d("perl",data);
                if (status.equalsIgnoreCase("okey")) {
//                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    String revtable = jo.getString("data");
                    Log.d("",revtable);

                    if (revtable.equalsIgnoreCase("1.0")) {
                        r1.setRating(1);
                    } else if (revtable.equalsIgnoreCase("2.0")) {
                        r1.setRating(2);
                    } else if (revtable.equalsIgnoreCase("3.0")) {
                        r1.setRating(3);
                    } else if (revtable.equalsIgnoreCase("4.0")) {
                        r1.setRating(4);
                    } else if (revtable.equalsIgnoreCase("5.0")) {
                        r1.setRating(5);

                    }

                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    } public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Mybookings.class);
        startActivity(b);
    }

}
