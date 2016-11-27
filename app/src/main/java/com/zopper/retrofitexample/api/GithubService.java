package com.zopper.retrofitexample.api;

import com.zopper.retrofitexample.models.Comments;
import com.zopper.retrofitexample.models.Issues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GithubService {

    String BASE_URL = "https://api.github.com/repos/";

    @GET("crashlytics/secureudid/issues?sort=updated")
    Call<List<Issues>> repoContributors();

    @GET("crashlytics/secureudid/issues/{number}/comments?sort=updated")
    Call<List<Comments>> issueComments(
            @Path("number") int groupId
    );

}
