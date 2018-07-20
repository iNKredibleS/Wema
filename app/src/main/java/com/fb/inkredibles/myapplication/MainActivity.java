package com.fb.inkredibles.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btGrid) Button btGrid;


    Button rakBtn;
    public static boolean archive = false;

    //TODO be sure to remove this button and intent
    @BindView(R.id.btnCreate) Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        rakBtn = findViewById(R.id.rakBtn);

        rakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RAKActivity.class);
                startActivity(i);
            }
        });

        btGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });



    }

    @OnClick(R.id.btnCreate)
    protected void launchCreate(){
        startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
    }

    @OnClick(R.id.btn_archive)
    protected void launchArchive(){
        archive = true;
        startActivity(new Intent (MainActivity.this, ArchiveActivity.class));
    }

    @OnClick(R.id.logout)
    protected void logout(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Log.i("homeactivity", "not logged out");
        }
    }

    public boolean getArchive(){
        return archive;
    }




}
