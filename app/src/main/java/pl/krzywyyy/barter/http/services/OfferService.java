package pl.krzywyyy.barter.http.services;

import java.util.List;

import pl.krzywyyy.barter.model.domain.Offer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OfferService {
    @POST("offers")
    Call<Offer> save(@Body Offer offer);

    @GET("offers")
    Call<List<Offer>> findAll();

    @GET("offers/{offerId}")
    Call<Offer> find(@Path("offerId") int offerId);

    @PUT("offers/{offerId}")
    Call<Offer> consider(@Path("offerId") int offerId, @Body Offer offer);

    @DELETE("offers/{offerId}")
    Call<Void> delete(@Path("offerId") int offerId, @Query("accepted") boolean accepted);
}
