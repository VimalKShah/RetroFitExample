step 1

public class ApiClient {
private static Retrofit retrofit = null;

private static Gson gson = new GsonBuilder()
.setLenient()
.create();

public static Retrofit getClient(String baseUrl) {
if(retrofit == null) {
retrofit = new Retrofit.Builder()
.baseUrl(baseUrl)
.addConverterFactory(GsonConverterFactory.create(gson))
.build();
}

return retrofit;
}
}

————————————————————————————

step 2

public class ApiServices {

public static final String BASE_URL = “https://xyz.com/api/”;

public static AccountService getAccountService() {
return ApiClient.getClient(BASE_URL).create(AccountService.class);
}

}

—————————————————————————————

step 3

make an interface public class

public interface AccountService {

@POST(“account_register”)
Call doRegister (@Body account.register.Request registerRequest);

@POST(“account_login”)
Call doLogin(@Body Request loginRequest);

@POST(“account_logout”)
Call doLogOut(@Bodyaccount.profile.Request logoutRequest);

@Multipart
@POST(“account_profile_upload-profile”)
Call doUploadImage(@Part MultipartBody.Part file, @Part(“user_id”) RequestBody userId, @Part(“sort_order”) RequestBody picOrder);

@GET(“account_profile_upload-profile/{user_id}”)
Call doGetProfileImages(@Path(“user_id”) int user_id);

@DELETE(“account_profile_upload-profile/id/{id}”+”/sort_order/{sort_order}”)
Call doRemoveImage(@Path(“id”) int user_id,@Path(“sort_order”)String sort_order);

}

————————————————————————————————

step 4

call retrofit on main activity
private AccountService accountService;;

accountService = ApiServices.getAccountService();

accountService.doLogin(loginRequest).enqueue(new Callback() {
@Override
public void onResponse(@NonNull Call call, @NonNull retrofit2.Response response) {
if (response.isSuccessful() && response.body()!=null) {
switch (response.body().getType()) {
case “failure”:
try {
edtEmailid.setError(response.body().getErrors().getEmail());
edtPassword.setError(response.body().getErrors().getPassword());
updateLoader(false);
} catch (Exception e) {
e.printStackTrace();
}

break;
case “success”:
try {
model = response.body().getData();
preference.createLoginSession(response.body().getData());
} catch (JSONException e) {
e.printStackTrace();
}

updateLoader(false);
goToMeetUpActivity();

break;
}
} else {
Toast.makeText(getApplicationContext(), R.string.alert_api_failure, Toast.LENGTH_LONG).show();
}
}

@Override
public void onFailure(@NonNull Call call, @NonNull Throwable t) {
Toast.makeText(getApplicationContext(), R.string.alert_api_failure, Toast.LENGTH_LONG).show();
updateLoader(false);
}
});

