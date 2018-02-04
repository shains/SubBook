package com.example.sharonhains.subbook;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String FILENAME = "subbook.sav";
    private EditText subcomment;
    private EditText subcharge;
    private EditText subname;
    private EditText subdate;
    private ListView prevSubscriptionList;


    private ArrayList<Subscription> sublist;
    private ArrayAdapter<Subscription> adapter;

    private Button newSubButton;
    private Button delSubButton; //= (Button) findViewById(R.id.delsub);
    private ViewFlipper vf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button newSubButton = (Button) findViewById(R.id.addsub);
        Button addButton = (Button) findViewById(R.id.add);
        Button backButton = (Button) findViewById(R.id.go_back);

        subcharge = (EditText) findViewById(R.id.subprice);
        //bodyText = (EditText) findViewById(R.id.body);
        vf = findViewById(R.id.viewFlipper);
        prevSubscriptionList = (ListView) findViewById(R.id.prevSubscriptionList);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                vf.showNext();
                setResult(RESULT_OK);
                String text = "testtext";
                //String text = bodyText.getText().toString();
                String charge = "4";
                Subscription newSub = new Subscription(text,charge);
                sublist.add(newSub);

                adapter.notifyDataSetChanged();

                saveInFile();

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                vf.showNext();
                setResult(RESULT_OK);
            }
        });

        newSubButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                addNewSubscription();

            }

        });


    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Subscription>(this,
                R.layout.list_item, sublist);
        prevSubscriptionList.setAdapter(adapter);
    }

    private void addNewSubscription(){
        String text = "testtext";
        String newCharge = subcharge.getText().toString();
        //int intCharge = Integer.parseInt(newCharge);
        Subscription newSub = new Subscription(text,newCharge);

        sublist.add(newSub);

        adapter.notifyDataSetChanged();

        saveInFile();
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-24

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();

            sublist = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            sublist = new ArrayList<Subscription>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(sublist, out);

            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    protected void onDestroy(){
        super.onDestroy();
        Log.i("In Destroy method","The app is closing");
    }

}
