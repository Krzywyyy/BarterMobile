package pl.krzywyyy.barter.retrofit;

import android.app.Application;

import pl.krzywyyy.barter.retrofit.components.DaggerRetrofitComponent;
import pl.krzywyyy.barter.retrofit.components.RetrofitComponent;
import pl.krzywyyy.barter.retrofit.modules.GsonModule;
import pl.krzywyyy.barter.retrofit.modules.RetrofitModule;

public class MyApplication extends Application {
    private RetrofitComponent retrofitComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofitComponent = DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule("http://192.168.1.104:8080"))
                .gsonModule(new GsonModule())
                .build();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }
}
