package pl.krzywyyy.barter.di.modules;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {
    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
