package pl.krzywyyy.barter.ui.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Witam w domu");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
