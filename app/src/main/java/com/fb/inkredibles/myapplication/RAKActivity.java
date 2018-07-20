package com.fb.inkredibles.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.inkredibles.myapplication.models.Rak;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RAKActivity extends AppCompatActivity {
    TextView rakTxt;
    int total, current, REQUEST_CODE = 20;
    Random rand;
    Button newRakBtn;
    ImageButton refreshBtn;
    ArrayList<Rak> rakList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rak);

        final ParseQuery<Rak> query = ParseQuery.getQuery(Rak.class);
        query.setLimit(10);

        rakList = new ArrayList<Rak>();

        //get total number of Rak objects from server
        try {
            total = query.count();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        rand = new Random();


        //set text box initially
        query.findInBackground(new FindCallback<Rak>() {
            @Override
            public void done(List<Rak> objects, ParseException e) {
                if(e == null) {
                    Log.d("FindSuccessful", "Finding RAK Successful");
                    int randomNum = rand.nextInt(total) + 1;

                    Rak currentRak = objects.get(randomNum - 1);

                    //store current randomNum so dont refresh to the same thing
                    current = randomNum;

                    //set text box
                    String title = currentRak.getTitle();

                    rakTxt = findViewById(R.id.rakTxt);
                    rakTxt.setText(title);

                } else {
                    Log.d("FindFailed", "Retrieving RAK unsuccessful");
                }


            }
        });

        //add refresh button feature
        refreshBtn = findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                query.findInBackground(new FindCallback<Rak>() {
                    @Override
                    public void done(List<Rak> objects, ParseException e) {
                        if(e == null) {
                            Log.d("FindSuccessful", "Finding RAK Successful");

                            int randomNum = rand.nextInt(total) + 1;

                            while(randomNum == current) {
                                randomNum = rand.nextInt(total) + 1;
                            }


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

        //add ability to create new RAK and post to database\
        newRakBtn = findViewById(R.id.newRakBtn);

        newRakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RAKActivity.this, NewRakPost.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }

    private String RAKGenerator(ParseQuery<Rak> query, final int total) {
        final String[] title = {""};

        query.findInBackground(new FindCallback<Rak>() {
            @Override
            public void done(List<Rak> objects, ParseException e) {
                if(e == null) {
                    Log.d("FindSuccessful", "Finding RAK Successful");
                    int randomNum = rand.nextInt(total) + 1;

                    Rak currentRak = objects.get(randomNum - 1);
                    //ask angie
                    title[0] = currentRak.getTitle();


                } else {
                    Log.d("FindFailed", "Retrieving RAK unsuccessful");
                }
            }
        });

        return title[0];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String newText = data.getExtras().getString("newRak");

           rakTxt.setText(newText);

           Rak newRak = new Rak();
           newRak.setTitle(newText);
           newRak.setUser(ParseUser.getCurrentUser());
           newRak.saveInBackground();


        }
    }



}
