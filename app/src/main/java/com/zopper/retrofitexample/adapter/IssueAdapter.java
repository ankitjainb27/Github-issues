package com.zopper.retrofitexample.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zopper.retrofitexample.CommentDialog;
import com.zopper.retrofitexample.Utils.DividerItemDecoration;
import com.zopper.retrofitexample.api.GithubService;
import com.zopper.retrofitexample.R;
import com.zopper.retrofitexample.models.Comments;
import com.zopper.retrofitexample.models.Issues;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankitjain1 on 27/11/16.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {

    private List<Issues> issuesList;
    private Context context;

    public IssueAdapter(Context context, List<Issues> issuesList) {
        this.context = context;
        this.issuesList = issuesList;
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue_row, parent, false);

        return new IssueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {
        final Issues issues = issuesList.get(position);
        holder.title.setText(issues.getTitle());
        if (issues.getBody().length() <= 50)
            holder.body.setText(issues.getBody());
        else
            holder.body.setText(issues.getBody().substring(0, 50) + "...");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((Activity) context).getFragmentManager();
                CommentDialog newFragment = CommentDialog
                        .newInstance(issues);
                newFragment.show(manager, "dialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return issuesList.size();
    }


    public class IssueViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body;
        public CardView cardView;

        public IssueViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.issue_title);
            body = (TextView) view.findViewById(R.id.issue_body);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
