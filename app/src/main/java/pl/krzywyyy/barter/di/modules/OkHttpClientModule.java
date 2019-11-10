package pl.krzywyyy.barter.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.krzywyyy.barter.utils.SharedPreferencesManager;

@Module
public class OkHttpClientModule {

    private final Context context;

    public OkHttpClientModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Authorization", SharedPreferencesManager.getToken(context));
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();
    }
}
