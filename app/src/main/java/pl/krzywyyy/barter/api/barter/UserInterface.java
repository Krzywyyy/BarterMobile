package pl.krzywyyy.barter.api.barter;

import pl.krzywyyy.barter.model.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {

    @POST("users/register")
    Call<Void> signUp(@Body User user);

    @POST("users/login")
    Call<Void> signIn(@Body User user);
}
