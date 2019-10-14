package com.android.mvvm.sample.view.social.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Posts {

    @SerializedName("userId")
    public Integer userId;
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;

    public List<Comments> comments = new ArrayList<>();

}
