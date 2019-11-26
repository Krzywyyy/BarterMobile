package pl.krzywyyy.barter.ui.main.useroffers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.api.OfferInterface;
import pl.krzywyyy.barter.model.domain.Offer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class UserOffersViewModel extends ViewModel {

    @Inject
    Retrofit retrofit;

    private MutableLiveData<List<Offer>> offers;

    public UserOffersViewModel() {
        MyApplication.appComponent.inject(this);
        offers = getOffers();
    }

    public MutableLiveData<List<Offer>> getUserOffers() {
        return offers;
    }

    private MutableLiveData<List<Offer>> getOffers() {
        OfferInterface offerService = retrofit.create(OfferInterface.class);

        Call<List<Offer>> call = offerService.findAll();

        MutableLiveData<List<Offer>> userOffers = new MutableLiveData<>();

        call.enqueue(new Callback<List<Offer>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userOffers.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {

            }
        });
        return userOffers;
    }
}
