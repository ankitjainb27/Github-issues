package com.zopper.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zopper.retrofitexample.adapter.IssueAdapter;
import com.zopper.retrofitexample.api.GithubService;
import com.zopper.retrofitexample.models.Issues;

import java.io.Serializable;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IssueAdapter mAdapter;
    List<Issues> issues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.issues_progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.issues_recycler_view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GithubService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        GithubService githubService = retrofit.create(GithubService.class);

        Call<List<Issues>> call = githubService.repoContributors();
        call.enqueue(new Callback<List<Issues>>() {
            @Override
            public void onResponse(Call<List<Issues>> call, Response<List<Issues>> response) {
                progressBar.setVisibility(View.GONE);
                issues = response.body();
                if (issues != null) {
                    mAdapter = new IssueAdapter(MainActivity.this, issues);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, R.string.try_again_later, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Issues>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, R.string.try_again_later, Toast.LENGTH_LONG).show();
            }
        });
    }
}
