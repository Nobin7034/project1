package com.example.organicfoodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Mybookings extends AppCompatActivity implements AdapterView.OnItemClickListener,JsonResponse {

    ListView l1;
    String[] itemname,desc,image,quantity,total,amount,item_id,sts;
    TextToSpeech textToSpeech;
    public static String textToSpeak, itemid,pr,stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookings);

        l1=(ListView) findViewById(R.id.lv1);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Mybookings.this;
        String q = "/bookings?id="+Login.logid;
        q = q.replace(" ", "%20");
        JR.execute(q);


//        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    int result = textToSpeech.setLanguage(Locale.US);
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Log.e("TTS", "Language not supported");
//                    } else {
//                        speakText();
//                    }
//                } else {
//                    Log.e("TTS", "Initialization failed");
//                }
//            }
//        });
    }

//    private void speakText() {
//        textToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (textToSpeech != null) {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
//        super.onDestroy();
//    }





    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                itemname = new String[ja1.length()];
                desc = new String[ja1.length()];
                quantity = new String[ja1.length()];
                total = new String[ja1.length()];
                item_id = new String[ja1.length()];
                amount = new String[ja1.length()];
                sts = new String[ja1.length()];


                image = new String[ja1.length()];
//                doc_id = new String[ja1.length()];
//                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    itemname[i] = ja1.getJSONObject(i).getString("item_name");
                    desc[i] = ja1.getJSONObject(i).getString("description");
                    quantity[i] = ja1.getJSONObject(i).getString("quantity");
                    total[i] = ja1.getJSONObject(i).getString("total");
                    item_id[i] = ja1.getJSONObject(i).getString("order_master_id");
                    amount[i] = ja1.getJSONObject(i).getString("amount");
                    sts[i] = ja1.getJSONObject(i).getString("status");
//                    output[i] = ja1.getJSONObject(i).getString("predicted");
                    image[i] = ja1.getJSONObject(i).getString("image");

//                    textToSpeak=output[i];
//                    doc_id[i] = ja1.getJSONObject(i).getString("doctor_id");
//                    value[i] = "First Name: " + firstname[i] + "\nLast Name: " + lname[i] +"\nPlace:"+place[i]+ "\nphone: " + phone[i] + "\nEmail: " + email[i]+"\nAppointment Date"+apdate[i]+"\nStatus:"+sts[i] +"\n" + "\n ";


                }
//                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                l1.setAdapter(ar);
                Custimage2 a = new Custimage2(this,image, itemname, desc, quantity,amount,sts);
                l1.setAdapter(a);
            }
        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    } public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemid=item_id[position];
        pr=total[position];
        stats=sts[position];

        if (stats.equalsIgnoreCase("delivered")) {

            final CharSequence[] items = {"Order Collected","Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Mybookings.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


                    if (items[item].equals("Order Collected")) {

                        JsonReq JR = new JsonReq();
                        JR.json_response = (JsonResponse) Mybookings.this;
                        String q = "/collected?id="+itemid;
                        q = q.replace(" ", "%20");
                        JR.execute(q);
                        Toast.makeText(getApplicationContext(),"Order Collected Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Mybookings.class));

                    }

                    else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }

                }

            });
            builder.show();
        }
        else if (stats.equalsIgnoreCase("collected")) {
            final CharSequence[] items = {"Rate And Review","Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Mybookings.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


                    if (items[item].equals("Rate And Review")) {

                        startActivity(new Intent(getApplicationContext(),Rateandreview.class));

                    }

                    else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }

                }

            });
            builder.show();

        }



    }
}
