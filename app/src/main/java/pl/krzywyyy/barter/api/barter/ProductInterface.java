package pl.krzywyyy.barter.api.barter;

import java.util.List;

import pl.krzywyyy.barter.model.domain.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductInterface {
    @POST("products")
    Call<Product> save(@Body Product product);

    @GET("products")
    Call<List<Product>> findAll(
            @Query("page") int page,
            @Query("category") String category,
            @Query("specialization") String specialization,
            @Query("searchText") String searchText,
            @Query("distance") Integer distance,
            @Query("latitude") Float latitude,
            @Query("longitude") Float longitude
            );

    @GET("products/my")
    Call<List<Product>> findAllUserProducts();

    @GET("products/{productId}")
    Call<Product> find(@Path("productId") int productId);

    @PUT("products/{productId}")
    Call<Product> update(@Path("productId") int productId, @Body Product product);

    @DELETE("products/{productId}")
    Call<Void> delete(@Path("productId") int productId);
}
