package com.fb.inkredibles.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fb.inkredibles.myapplication.models.Rak;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Random;

public class RAKActivity extends AppCompatActivity {
    TextView rakTxt;
    int total, randomNum;
    Random rand;
    Button refreshBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rak);

        final ParseQuery<Rak> query = ParseQuery.getQuery(Rak.class);
        query.setLimit(10);

        //get total number of Rak objects from server
        try {
            total = query.count();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        rand = new Random();


        query.findInBackground(new FindCallback<Rak>() {
            @Override
            public void done(List<Rak> objects, ParseException e) {
                if(e == null) {
                    Log.d("FindSuccessful", "Finding RAK Successful");
                    randomNum = rand.nextInt(total) + 1;

                    Rak currentRak = objects.get(randomNum - 1);
                    String title = currentRak.getTitle();

                    rakTxt = findViewById(R.id.rakTxt);
                    rakTxt.setText(title);

                } else {
                    Log.d("FindFailed", "Retrieving RAK unsuccessful");
                }


            }
        });

        refreshBtn = findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                query.findInBackground(new FindCallback<Rak>() {
                    @Override
                    public void done(List<Rak> objects, ParseException e) {
                        if(e == null) {
                            Log.d("FindSuccessful", "Finding RAK Successful");
                            randomNum = rand.nextInt(total) + 1;

                            Rak currentRak = objects.get(randomNum - 1);
                            String title = currentRak.getTitle();

                            rakTxt = findViewById(R.id.rakTxt);
                            rakTxt.setText(title);

                        } else {
                            Log.d("FindFailed", "Retrieving RAK unsuccessful");
                        }


                    }
                });

                //String title = RAKGenerator(query, total);
                //System.out.println("************" + title);


            }
        });


    }

    private String RAKGenerator(ParseQuery<Rak> query, final int total) {
        final String title = "";

        query.findInBackground(new FindCallback<Rak>() {
            @Override
            public void done(List<Rak> objects, ParseException e) {
                if(e == null) {
                    Log.d("FindSuccessful", "Finding RAK Successful");
                    randomNum = rand.nextInt(total) + 1;

                    Rak currentRak = objects.get(randomNum - 1);
                    //ask angie
                    //title = currentRak.getTitle();


                } else {
                    Log.d("FindFailed", "Retrieving RAK unsuccessful");
                }


            }
        });

        return title;
    }




}
