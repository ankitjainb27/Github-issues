package com.zopper.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zopper.retrofitexample.R;
import com.zopper.retrofitexample.models.Comments;

import java.util.List;

/**
 * Created by ankitjain1 on 27/11/16.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {
    private List<Comments> commentsList;

    public CommentsAdapter(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_row, parent, false);

        return new CommentsAdapter.CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comments comments = commentsList.get(position);
        holder.username.setText(comments.getUser().getLogin());
        holder.body.setText(comments.getBody());
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView username, body;

        public CommentViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            body = (TextView) view.findViewById(R.id.comment_body);
        }
    }
}
