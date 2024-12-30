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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Userviewdieteam extends AppCompatActivity implements JsonResponse,AdapterView.OnItemClickListener{

    ListView l1;
    String[] name,place,phone,email,diet_id,value;
    Button b1;
    TextToSpeech textToSpeech;
    public static  String dietid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewdieteam);

        l1=(ListView) findViewById(R.id.lv1);
        b1=(Button) findViewById(R.id.myreq);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Viewmyrequests.class));
            }
        });
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Userviewdieteam.this;
        String q = "/viewdietteam";
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

                name = new String[ja1.length()];
                place = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                diet_id = new String[ja1.length()];
                value = new String[ja1.length()];

;

//                doc_id = new String[ja1.length()];
//                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    name[i] = ja1.getJSONObject(i).getString("name");

                    place[i] = ja1.getJSONObject(i).getString("place");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    diet_id[i] = ja1.getJSONObject(i).getString("diet_id");
                    email[i] = ja1.getJSONObject(i).getString("email");

//                    pdate[i] = ja1.getJSONObject(i).getString("date");
//                    output[i] = ja1.getJSONObject(i).getString("predicted");


//                    textToSpeak=output[i];
//                    doc_id[i] = ja1.getJSONObject(i).getString("doctor_id");
                    value[i] = "Diet Team: " + name[i] + "\nphone: " + phone[i] + "\nEmail: " + email[i] +"\n" + "\n ";


                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);
//                Custimage a = new Custimage(this,image, storename, buldingname, place, phone,email);
//                l1.setAdapter(a);
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
        dietid=diet_id[position];
        final CharSequence[] items = {"Send Request","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Userviewdieteam.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Send Request")) {


                    startActivity(new Intent(getApplicationContext(),Senddietreq.class));
                }

                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });
        builder.show();
    }

}