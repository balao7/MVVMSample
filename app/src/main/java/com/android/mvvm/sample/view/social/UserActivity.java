package com.android.mvvm.sample.view.social;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.mvvm.sample.R;
import com.android.mvvm.sample.network.ApiClient;
import com.android.mvvm.sample.view.social.api.API;
import com.android.mvvm.sample.view.social.models.Albums;
import com.android.mvvm.sample.view.social.models.Comments;
import com.android.mvvm.sample.view.social.models.Photos;
import com.android.mvvm.sample.view.social.models.Posts;
import com.android.mvvm.sample.view.social.models.Todos;
import com.android.mvvm.sample.view.social.models.Users;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    public static final String TAG = "UserActivity";
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.api = ApiClient.getApiClient().create(API.class);

        // Get Users
        // getUsers();

        // Get User Posts
        getUserPosts();

        // Get Comments Against Posts.
        // getUserComments();

        // Get User Albums
        // getUserAlbums();

        // Get Photos against Albums
        // getAlbumPhotos();

        // Get User Todos.
        // getUserTodos();
    }

    private void getUsers() {
        Call<List<Users>> usersCall = this.api.getUsers();
        usersCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.body().isEmpty()) {
                    Log.d(TAG, "Users: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e(TAG, "Get User error: " + t.getMessage());
                Toast.makeText(UserActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserPosts() {
        Call<List<Posts>> postsCall = this.api.getUserPosts(1);
        postsCall.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, final Response<List<Posts>> response) {
                if (!response.body().isEmpty()) {
                    Log.d(TAG, "Posts: " + response.body().size());
                    for (int i=0; i<response.body().size(); i++) {
                        final int index = i;
                        Integer postId = response.body().get(index).id;
                        Call<List<Comments>> commentsCall = api.getUserComments(postId);
                        commentsCall.enqueue(new Callback<List<Comments>>() {
                            @Override
                            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> commentsResponse) {
                                if (!commentsResponse.body().isEmpty()) {
                                    Log.d(TAG, "Comments: " + commentsResponse.body().size());
                                    response.body().get(index).comments.add(commentsResponse.body().get(0));
                                    response.body().get(index).comments.add(commentsResponse.body().get(1));
                                }

                                
                            }

                            @Override
                            public void onFailure(Call<List<Comments>> call, Throwable t) {
                                Log.e(TAG, "Get Comments: " + t.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.e(TAG, "Get User Posts: " + t.getMessage());
                Toast.makeText(UserActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserComments() {
        Call<List<Comments>> postsCall = this.api.getUserComments(1);
        postsCall.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.body().isEmpty()) {
                    Log.d(TAG, "Comments: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Log.e(TAG, "Get Comments: " + t.getMessage());
                Toast.makeText(UserActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserAlbums() {
        Call<List<Albums>> postsCall = this.api.getUserAlbums(1);
        postsCall.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if (!response.body().isEmpty()) {
                    Log.d(TAG, "Albums: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Log.e(TAG, "Get User Albums: " + t.getMessage());
                Toast.makeText(UserActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAlbumPhotos() {
        Call<List<Photos>> postsCall = this.api.getAlbumPhotos(1);
        postsCall.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (!response.body().isEmpty()) {
                    Log.d(TAG, "Photos: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Log.e(TAG, "Get Photos: " + t.getMessage());
                Toast.makeText(UserActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserTodos() {
        Call<List<Todos>> postsCall = this.api.getUserTodos(1);
        postsCall.enqueue(new Callback<List<Todos>>() {
            @Override
            public void onResponse(Call<List<Todos>> call, Response<List<Todos>> response) {
                if (!response.body().isEmpty()) {
                    Log.d(TAG, "Todos: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Todos>> call, Throwable t) {
                Log.e(TAG, "Get Todos: " + t.getMessage());
                Toast.makeText(UserActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class GetCommentsThread extends Thread {

    }
}
