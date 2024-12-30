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

public class Viewitems extends AppCompatActivity implements AdapterView.OnItemClickListener, JsonResponse{
    ListView l1;
    String[] itemname,desc,image,price,storename,stock,item_id;
    TextToSpeech textToSpeech;
    public static String textToSpeak, itemid,pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstores);

        l1=(ListView) findViewById(R.id.lv1);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewitems.this;
        String q = "/viewitems?id="+Viewstores.storeid;
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
                storename = new String[ja1.length()];
                itemname = new String[ja1.length()];
                desc = new String[ja1.length()];
                price = new String[ja1.length()];
                stock = new String[ja1.length()];
                item_id = new String[ja1.length()];


                image = new String[ja1.length()];
//                doc_id = new String[ja1.length()];
//                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    storename[i] = ja1.getJSONObject(i).getString("store_name");
                    itemname[i] = ja1.getJSONObject(i).getString("item_name");
                    desc[i] = ja1.getJSONObject(i).getString("description");
                    price[i] = ja1.getJSONObject(i).getString("price");
                    stock[i] = ja1.getJSONObject(i).getString("stock");
                    item_id[i] = ja1.getJSONObject(i).getString("item_id");
//                    pdate[i] = ja1.getJSONObject(i).getString("date");
//                    output[i] = ja1.getJSONObject(i).getString("predicted");
                    image[i] = ja1.getJSONObject(i).getString("image");

//                    textToSpeak=output[i];
//                    doc_id[i] = ja1.getJSONObject(i).getString("doctor_id");
//                    value[i] = "First Name: " + firstname[i] + "\nLast Name: " + lname[i] +"\nPlace:"+place[i]+ "\nphone: " + phone[i] + "\nEmail: " + email[i]+"\nAppointment Date"+apdate[i]+"\nStatus:"+sts[i] +"\n" + "\n ";


                }
//                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                l1.setAdapter(ar);
                Custimage1 a = new Custimage1(this,image, storename, itemname, desc, price,stock);
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
        Intent b=new Intent(getApplicationContext(),Viewstores.class);
        startActivity(b);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemid=item_id[position];
        pr=price[position];

        final CharSequence[] items = {"Book Items","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewitems.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Book Items")) {

                    startActivity(new Intent(getApplicationContext(),Bookitems.class));

                }

                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });
        builder.show();
    }
}
