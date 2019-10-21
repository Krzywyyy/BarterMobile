package pl.krzywyyy.barter.retrofit.api;

import pl.krzywyyy.barter.model.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {

    @POST("users/register")
    Call signUp(@Body User user);

    @POST("users/login")
    Call signIn(@Body User user);
}
