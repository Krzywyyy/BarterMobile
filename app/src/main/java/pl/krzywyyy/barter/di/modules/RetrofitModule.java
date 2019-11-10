package pl.krzywyyy.barter.di.modules;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {GsonModule.class, OkHttpClientModule.class})
public class RetrofitModule {

    private final String urlPath;

    public RetrofitModule(String urlPath) {
        this.urlPath = urlPath;
    }

    @Inject
    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(urlPath)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
