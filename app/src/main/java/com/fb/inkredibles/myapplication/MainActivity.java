package com.fb.inkredibles.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button rakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rakBtn = findViewById(R.id.rakBtn);

        rakBtn.setOnClickListener(new View.OnClickListener() {

            @Overridefi
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RAKActivity.class);
                startActivity(i);
            }
        });

    }
}
