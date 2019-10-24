package pl.krzywyyy.barter.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pl.krzywyyy.barter.authentication.LoginFragment;
import pl.krzywyyy.barter.authentication.RegisterFragment;
import pl.krzywyyy.barter.di.modules.RetrofitModule;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface AppComponent {
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
}
