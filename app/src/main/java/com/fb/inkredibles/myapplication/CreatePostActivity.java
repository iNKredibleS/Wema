package com.fb.inkredibles.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.inkredibles.myapplication.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class CreatePostActivity extends AppCompatActivity {

    //bind all of the views
    @BindView(R.id.Title) EditText et_title;
    @BindView(R.id.et_message) EditText et_message;
    @BindView(R.id.switch_give_rec) Switch switch_give_rec;
    @BindView(R.id.switch_pub_pri) Switch switch_pub_pri;
    @BindView(R.id.pictureHolder) ImageView pictureHolder;
    @BindView(R.id.tv_give_rec) TextView tvGiveRec;
    @BindView(R.id.tv_pub_pri) TextView tvPubPri;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_GALLERY_IMAGE = 2;
    private static File filesDir;
    private String type;
    private String privacy;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private ParseFile parseFile;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        filesDir = getFilesDir(); //this seems fishy

        //butterknife bind
        ButterKnife.bind(this);

        //TODO: get title from rak passed to this post
        //if we reuse this activity for another entry point to creating a post
        //we may need a boolean to check what activity launched this activity
        //for now I am operating under the assumption that the RAK activity
        //launched this activity

        et_title.setText("RAK #1");
        //set the cursor position to end of input title
        //found on stack overflow
        int position = et_title.length();
        Editable etext = et_title.getText();
        Selection.setSelection(etext, position);

        //setting up switches
        switch_pub_pri.setChecked(true);
        switch_give_rec.setChecked(true);
        tvGiveRec.setText("Given");
        tvPubPri.setText("Public");

        //default type and privacy values
        type = "give";
        privacy = "public";




//        switch_pub_pri.setTextOn("Public"); // displayed text of the Switch whenever it is in checked or on state
//        switch_pub_pri.setTextOff("Private");


    }

    //TODO change to teriary format
    @OnCheckedChanged(R.id.switch_give_rec)
    void giveRec(CompoundButton compoundButton, boolean checked){
        if(checked) {
            tvGiveRec.setText("Given");
            type = "give";
        } else {
            tvGiveRec.setText("Received");
            type = "receive";
        }

    }

    @OnCheckedChanged(R.id.switch_pub_pri)
    void pubPri(CompoundButton compoundButton, boolean checked){
        if(checked) {
            tvPubPri.setText("Public");
            privacy = "public";
        } else {
            tvPubPri.setText("Just for me");
            privacy = "private";
        }
    }




    //on post button clicked
    @OnClick(R.id.btn_post)
    protected void postButtonClicked(){
        final String title = et_title.getText().toString();
        final String message = et_message.getText().toString();
        final ParseUser user = ParseUser.getCurrentUser();
        final String finalPrivacy = privacy;
        final String finalType = type;

        if(file != null) parseFile = new ParseFile(file);

        //set up getting image
        //get final Strings for switches too

        createPost(title, message, user, parseFile, finalPrivacy, finalType); //eventually createPost (title, message, user, image, privacy, type)
    }

    @OnClick(R.id.btn_gallery)
    protected void gallery(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent , REQUEST_GALLERY_IMAGE);
    }

    @OnClick(R.id.btn_camera)
    protected void camera() {
        if(isStoragePermissionGranted()) {
            dispatchTakePictureIntent();
        }
    }

    //switches
    //create a protected/public? boolean for the states and then in create post
    //set the fields of the post to the give/receive, public/private based on these
    //booleans

    //create post
    //set the title, message, user, image, privacy, give, receive
    private void createPost(String title, String message, ParseUser user, ParseFile parseFile, String privacy, String type) {
        final Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setMessage(message);
        newPost.setUser(user);
        if(parseFile != null) newPost.setImage(parseFile);
        newPost.setPrivacy(privacy);
        newPost.setType(type);

        newPost.saveInBackground(
                new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("CreatePostActivity", "create post success");
                            Toast.makeText(CreatePostActivity.this, "Post Created", Toast.LENGTH_SHORT).show();

                        } else {
                            e.printStackTrace();
                        }
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            pictureHolder.setImageBitmap(imageBitmap);
            file = persistImage(imageBitmap, "pic1");
        }else if(requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            pictureHolder.setImageURI(imageUri);
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                file = persistImage(imageBitmap, "pic2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static File  persistImage(Bitmap bitmap, String name) {
        File imageFile = new File(filesDir, name + ".jpg");
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.d("Create Post Fragment", "Error writing bitmap", e);
        }
        return imageFile;

    }





    /*Requests permission to read external storage*/
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("Create Post Fragment","Permission is granted");
                return true;
            } else {

                Log.v("Create Post Fragment","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            Log.v("Create Post Fragment","Permission is granted");
            return true;
        }
    }

  /*dispatches an intent to take a picture*/
  private void dispatchTakePictureIntent() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    }

}


