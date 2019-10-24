package pl.krzywyyy.barter.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pl.krzywyyy.barter.authenticationActivity.LoginFragment;
import pl.krzywyyy.barter.di.modules.RetrofitModule;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface AppComponent {
    void inject(LoginFragment loginFragment);
}
