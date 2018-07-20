package com.fb.inkredibles.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewRakPost extends AppCompatActivity {

    EditText newRakTxt;
    Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rak_post);


        //store edit text into string
        newRakTxt = findViewById(R.id.newRakTxt);

        //send new Rak back to parent intent
        postBtn = findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent data = new Intent();

                data.putExtra("newRak", newRakTxt.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
