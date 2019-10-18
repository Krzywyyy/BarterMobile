package pl.krzywyyy.barter.http.retrofit.components;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import pl.krzywyyy.barter.http.retrofit.modules.GsonModule;

@Singleton
@Component(modules = GsonModule.class)
public interface GsonComponent {
    Gson getGson();
}
