package pl.krzywyyy.barter.api;

import java.util.List;

import pl.krzywyyy.barter.model.domain.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductInterface {
    @POST("products")
    Call<Product> save(@Body Product product);

    @GET("products")
    Call<List<Product>> findAll();

    @GET("products/{productId}")
    Call<Product> find(@Path("productId") int productId);

    @PUT("products/{productId}")
    Call<Product> update(@Path("productId") int productId, @Body Product product);

    @DELETE("products/{productId}")
    Call<Void> delete(@Path("productId") int productId);
}
