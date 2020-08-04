package com.example.retrofitexample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterface {

    @GET("/api/unknown")
    Call<MultipleResources> getResources();

    @POST("/api/user")
    Call<User> createUser(@Body User user);

    @GET("api/users?")
    Call<UserList> doGetUsrList(@Query("page") String page);

    @FormUrlEncoded
    @POST("api/users")
    Call<CreateUserResponse> doCreateUserListWithField(@Field("name") String name, @Field("job") String job);
}
