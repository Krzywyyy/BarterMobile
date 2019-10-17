package pl.krzywyyy.barter.http;

import pl.krzywyyy.barter.model.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/register")
    Call signUp(@Body User user);

    @POST("users/login")
    Call signIn(@Body User user);

}
