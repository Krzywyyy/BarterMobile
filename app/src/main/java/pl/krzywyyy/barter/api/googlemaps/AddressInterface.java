package pl.krzywyyy.barter.api.googlemaps;

import pl.krzywyyy.barter.model.maps.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddressInterface {
    @GET("geocode/json")
    Call<Result> getCoordinates(@Query("address") String address, @Query("key") String apiKey);
}
