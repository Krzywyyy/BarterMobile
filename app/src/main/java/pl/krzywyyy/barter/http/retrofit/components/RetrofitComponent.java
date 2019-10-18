package pl.krzywyyy.barter.http.retrofit.components;

import javax.inject.Singleton;

import dagger.Component;
import pl.krzywyyy.barter.http.retrofit.modules.GsonModule;
import pl.krzywyyy.barter.http.retrofit.modules.RetrofitModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RetrofitModule.class, GsonModule.class})
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
