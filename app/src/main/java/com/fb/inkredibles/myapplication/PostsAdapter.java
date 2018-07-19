package com.fb.inkredibles.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fb.inkredibles.myapplication.models.Post;
import com.parse.ParseImageView;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post>mPosts;
    private Context context;

    // Pass in the contact array into the constructor
    public PostsAdapter(List<Post> posts) {
        mPosts = posts;
    }


    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvTitle;
        public ParseImageView ivPostImageView;


        public  ViewHolder(View itemView){
            super(itemView);
            //tvMessage = (TextView) itemView.findViewById()
            //tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            //TODO: use title and not message
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivPostImageView = (ParseImageView) itemView.findViewById(R.id.ivPostImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("PostsAdapter","Clicked view");
            Intent intent = new Intent(context,PostActivity.class );
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                Post selectedPost = mPosts.get(position);
                //for parcels to be defined, remember to add the parcel dependencies in the build.gradle file
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(selectedPost));
                Pair<View, String> p1 = Pair.create((View)ivPostImageView, "postPicTransition");
                Pair<View,String> p2 = Pair.create((View)tvTitle, "titleTransition");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, p1,p2);
                context.startActivity(intent, options.toBundle());
//                context.startActivity(intent);
            }

        }
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
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
        // Get the post at the current position
        Post post = mPosts.get(position);
        viewHolder.tvTitle.setText(post.getTitle());
        //ParseFile file = post.getImage();
        viewHolder.ivPostImageView.setParseFile(post.getImage());
        viewHolder.ivPostImageView.loadInBackground();
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    //clean all the elements in the recycler
    public void clear(){
        mPosts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> posts){
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

}
