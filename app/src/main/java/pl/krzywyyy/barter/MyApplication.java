package pl.krzywyyy.barter;

import android.app.Application;

import pl.krzywyyy.barter.di.components.AppComponent;
import pl.krzywyyy.barter.di.components.DaggerAppComponent;
import pl.krzywyyy.barter.di.modules.GsonModule;
import pl.krzywyyy.barter.di.modules.RetrofitModule;

public class MyApplication extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule("http://192.168.1.104:8080"))
                .gsonModule(new GsonModule())
                .build();
    }
}
