package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MultipleResources {

    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPage;
    @SerializedName("data")
    public List<Data> dataList = null;

    public class Data {
        @SerializedName("id")
        public Integer id;
        @SerializedName("name")
        public String name;
        @SerializedName("year")
        public Integer year;
        @SerializedName("pantone_value")
        public String pantone_Value;
    }
}
