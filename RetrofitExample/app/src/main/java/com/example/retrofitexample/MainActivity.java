package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mResponseText;
    private APIInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResponseText = findViewById(R.id.response_text);
        mApiInterface = APIClient.getClient().create(APIInterface.class);

        Call<MultipleResources> call = mApiInterface.getResources();
        call.enqueue(new Callback<MultipleResources>() {
            @Override
            public void onResponse(Call<MultipleResources> call, Response<MultipleResources> response) {
                Log.d(TAG, "onResponse: " + response.body());

                StringBuilder displayResponse = new StringBuilder();

                MultipleResources resources = response.body();
                Integer page = resources.page;
                Integer total = resources.total;
                Integer totalPage = resources.totalPage;

                List<MultipleResources.Data> dataList = resources.dataList;

                displayResponse.append(page).append(" Page\n").append(total).append(" Total\n").append(totalPage).append(" TotalPage\n");

                for(MultipleResources.Data data : dataList) {
                    displayResponse.append(data.name).append(" ").append(data.id).append(" ").append(data.year).append(" ").append(data.pantone_Value).append("\n");
                }

                mResponseText.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<MultipleResources> call, Throwable t) {
                Log.d(TAG, "Call onFailure: " + t.getLocalizedMessage());
                call.cancel();
            }
        });

        final User user = new User("Vimal", "Android Developer");
        Call<User> callUser = mApiInterface.createUser(user);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: " + response.body());
                User responseUser = response.body();
                Toast.makeText(getApplicationContext(), responseUser.name + " " + responseUser.job + "  " + responseUser.createdAt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "CallUser onFailure: " + t.getLocalizedMessage());
                call.cancel();
            }
        });

        Call<UserList> userListCall = mApiInterface.doGetUsrList("2");
        userListCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.d(TAG, "onResponse: " + response.body());
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPage;
                List<UserList.Data> dataList = userList.dataList;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Data data : dataList) {
                    Toast.makeText(getApplicationContext(), "id : " + data.id + " name: " + data.firstName + " " + data.lastName + " avatar: " + data.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d(TAG, "UserListCall onFailure: " + t.getLocalizedMessage());
                call.cancel();
            }
        });

        Call<CreateUserResponse> responseCall = mApiInterface.doCreateUserListWithField("morpheus", "leader");
        responseCall.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                CreateUserResponse userResponse = response.body();
                Toast.makeText(getApplicationContext(), "id : " + userResponse.id + " name: " + userResponse.name + " job: " + userResponse.job + " createdAt: " + userResponse.createdAt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                Log.d(TAG, "ResponseCall onFailure: " + t.getLocalizedMessage());
                call.cancel();
            }
        });
    }
}
