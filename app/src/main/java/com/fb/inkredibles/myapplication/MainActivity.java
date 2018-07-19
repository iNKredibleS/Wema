package com.fb.inkredibles.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btGrid) Button btGrid;


    Button rakBtn;

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


}
