package com.fb.inkredibles.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.fb.inkredibles.myapplication.models.Post;
import com.parse.ParseImageView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity {
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.ivPostImage) ParseImageView ivPostImg;
    @BindView(R.id.tvMessage) TextView tvMessage;

    private String bodyText;
    private String title;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        String title = post.getTitle() == null? "No title" : post.getTitle();
        tvTitle.setText(title);
        tvMessage.setText(post.getMessage());
        ivPostImg.setParseFile(post.getImage());
        ivPostImg.loadInBackground();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
