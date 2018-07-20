package com.fb.inkredibles.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.fb.inkredibles.myapplication.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    private static final  int SPAN_COUNT = 3;


    private ArrayList<Post> posts;
    private RecyclerView rvPosts;
    private PostsAdapter adapter;
    private SwipeRefreshLayout swipeContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        //get the recyclerview
        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        //initialize posts
        posts = new ArrayList<>();
        //get the swipe container
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (adapter != null){ //just in case the adapter has not yet been initialized.
                    adapter.clear();
                    final Post.Query postsQuery = new Post.Query();
                    postsQuery.getTop().withUser();

                    postsQuery.findInBackground(new FindCallback<Post>() {
                        @Override
                        public void done(List<Post> objects, ParseException e) {
                            if (e == null){
                                adapter.addAll(objects);
                                swipeContainer.setRefreshing(false);
                            }else{
                                e.printStackTrace();
                            }
                        }
                    });


                }
            }
        });
        loadPosts();
    }

    private void loadPosts(){
        //get the top posts
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null){
                    for (int i = 0; i < objects.size(); i++){
                        posts.add(objects.get(i));
                        Log.d("Post", posts.get(i).getMessage());
                    }
                    //create a posts adapter
                    adapter = new PostsAdapter(posts);
                    //attach the adapter to the recyclerview to populate items
                    rvPosts.setAdapter(adapter);
                    //set the layout manager to position the items
                    StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
                    rvPosts.setLayoutManager(mLayoutManager);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }


}
