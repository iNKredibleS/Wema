package com.fb.inkredibles.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.fb.inkredibles.myapplication.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePost extends AppCompatActivity {

    //bind all of the views
    @BindView(R.id.Title) EditText et_title;
    @BindView(R.id.et_message) EditText et_message;
    @BindView(R.id.switch_give_rec) Switch switch_give_rec;
    @BindView(R.id.switch_pub_pri) Switch switch_pub_pri;
    @BindView(R.id.iv_post_pic) ImageView iv_post_pic;
    //gallery button
    //camera button



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
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

    }

    //on post button clicked
    @OnClick(R.id.btn_post)
    protected void postButtonClicked(){
        final String title = et_title.getText().toString();
        final String message = et_message.getText().toString();
        final ParseUser user = ParseUser.getCurrentUser();

        //set up getting image
        //get final Strings for switches too

        createPost(title, message, user); //eventually createPost (title, message, user, image, privacy, type)
    }
    //get the edited text fields and image if not null
    //get the state of the switches
    //pass to function that creates post

    //switches
    //create a protected/public? boolean for the states and then in create post
    //set the fields of the post to the give/receive, public/private based on these
    //booleans

    //create post
    //set the title, message, user, image, privacy, give, receive
    private void createPost(String title, String message, ParseUser user) {
        final Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setMessage(message);
        newPost.setUser(user);

        newPost.saveInBackground(
                new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("CreatePost", "create post success");

                        } else {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
