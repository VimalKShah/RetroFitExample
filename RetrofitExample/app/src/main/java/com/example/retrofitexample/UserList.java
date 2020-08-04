package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {

    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPage;
    @SerializedName("data")
    public List<UserList.Data> dataList = null;

    public class Data {
        @SerializedName("id")
        public Integer id;
        @SerializedName("first_name")
        public String firstName;
        @SerializedName("last_name")
        public String lastName;
        @SerializedName("avatar")
        public String avatar;
    }
}
