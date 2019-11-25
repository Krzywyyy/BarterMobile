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

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (SharedPreferencesManager.containsToken(context)) {
                        request = request.newBuilder()
                                .addHeader("Authorization", SharedPreferencesManager.getToken(context))
                                .build();
                    }
                    return chain.proceed(request);
                });

        return okBuilder.build();
    }
}
