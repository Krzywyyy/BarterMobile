package pl.krzywyyy.barter.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pl.krzywyyy.barter.ui.authentication.LoginFragment;
import pl.krzywyyy.barter.ui.authentication.RegisterFragment;
import pl.krzywyyy.barter.di.modules.RetrofitModule;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface AppComponent {
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
}
