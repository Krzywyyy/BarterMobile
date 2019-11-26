package pl.krzywyyy.barter.ui.main.offer;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import pl.krzywyyy.barter.MyApplication;
import pl.krzywyyy.barter.R;
import pl.krzywyyy.barter.api.OfferInterface;
import pl.krzywyyy.barter.model.domain.Offer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

@Getter
@Setter
public class OfferViewModel extends BaseObservable {

    @Inject
    Retrofit retrofit;

    private Offer offer;
    private MutableLiveData<Boolean> offerDone;

    OfferViewModel() {
        MyApplication.appComponent.inject(this);
        offerDone = new MutableLiveData<>();
        offer = new Offer();
    }

    void makeOffer(Context context, int productId) {
        OfferInterface offerService = retrofit.create(OfferInterface.class);

        offer.setProductId(productId);

        Call<Offer> call = offerService.save(offer);

        call.enqueue(new Callback<Offer>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, R.string.successfully_added_offer, Toast.LENGTH_SHORT).show();
                    offerDone.setValue(true);
                } else {
                    Toast.makeText(context, R.string.unsuccessfully_added_offer, Toast.LENGTH_SHORT).show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Toast.makeText(context, R.string.failed_retrofit_request_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
