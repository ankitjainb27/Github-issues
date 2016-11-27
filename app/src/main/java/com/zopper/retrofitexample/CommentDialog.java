package com.zopper.retrofitexample;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zopper.retrofitexample.Utils.DividerItemDecoration;
import com.zopper.retrofitexample.adapter.CommentsAdapter;
import com.zopper.retrofitexample.api.GithubService;
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

public class CommentDialog extends DialogFragment {

    public static CommentDialog newInstance(Issues issues) {
        CommentDialog frag = new CommentDialog();
        Bundle args = new Bundle();
        args.putSerializable("issues", issues);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Issues issues = (Issues) getArguments().getSerializable("issues");
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.comment);
        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.dialog_progress_bar);
        final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.dialog_recycler_view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GithubService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        GithubService githubService = retrofit.create(GithubService.class);

        Call<List<Comments>> call =
                githubService.issueComments(issues.getNumber());
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                progressBar.setVisibility(View.GONE);
                List<Comments> comments = response.body();
                if (comments != null) {
                    CommentsAdapter commentsAdapter = new CommentsAdapter(comments);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(commentsAdapter);
                } else {
                    progressBar.setVisibility(View.GONE);
                    dialog.dismiss();
                    Toast.makeText(getActivity(), R.string.try_again_later, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                dialog.dismiss();
                Toast.makeText(getActivity(), R.string.try_again_later, Toast.LENGTH_LONG).show();
            }
        });

        return dialog;
    }
}
