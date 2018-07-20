package com.fb.inkredibles.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fb.inkredibles.myapplication.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ArchiveActivity extends AppCompatActivity {

    private ArrayList<Post> archivedPosts;
    private RecyclerView rvArchivePosts;
    private PostsAdapter archiveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        //get the recyclerview
        rvArchivePosts = (RecyclerView) findViewById(R.id.ArchiveRecyclerView);
        //initialize posts
        archivedPosts = new ArrayList<>();
        loadArchives();
    }

    private void loadArchives(){
        //get the top posts
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getPrivate().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null){
                    for (int i = 0; i < objects.size(); i++){
                        archivedPosts.add(objects.get(i));
                        Log.d("Post", archivedPosts.get(i).getMessage());
                    }
                    //create a posts adapter
                    archiveAdapter = new PostsAdapter(archivedPosts);
                    //attach the adapter to the recyclerview to populate items
                    rvArchivePosts.setAdapter(archiveAdapter);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(ArchiveActivity.this);

                    rvArchivePosts.setLayoutManager(mLayoutManager);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }
}
