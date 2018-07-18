package com.fb.inkredibles.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fb.inkredibles.myapplication.models.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post>mPosts;

    // Pass in the contact array into the constructor
    public PostsAdapter(List<Post> posts) {
        mPosts = posts;
    }


    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvMessage;
        public TextView tvUsername;

        public  ViewHolder(View itemView){
            super(itemView);
            //tvMessage = (TextView) itemView.findViewById()

        }

        @Override
        public void onClick(View view) {
            Log.d("PostsAdapter","Clicked view");

        }
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_post, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

}
