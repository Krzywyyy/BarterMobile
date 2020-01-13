package pl.krzywyyy.barter.di.googlemapsretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleMapsRetrofit {

    private static Retrofit retrofit;
    private static String googleMapsApi = "https://maps.googleapis.com/maps/api/";

    private GoogleMapsRetrofit() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(googleMapsApi)
                    .build();
        }
        return retrofit;
    }
}
