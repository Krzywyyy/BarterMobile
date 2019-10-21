package pl.krzywyyy.barter.retrofit.components;

import javax.inject.Singleton;

import dagger.Component;
import pl.krzywyyy.barter.retrofit.modules.GsonModule;
import pl.krzywyyy.barter.retrofit.modules.RetrofitModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RetrofitModule.class, GsonModule.class})
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
