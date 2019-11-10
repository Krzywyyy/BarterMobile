package pl.krzywyyy.barter.ui.main.userproducts;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pl.krzywyyy.barter.model.domain.ProductView;

public class UserProductsViewModel extends ViewModel {

    private MutableLiveData<List<ProductView>> productViews;

    public UserProductsViewModel() {
        UserProductsRepository userProductsRepository = UserProductsRepository.getInstance();
        productViews = userProductsRepository.getUserProducts();
    }

    public MutableLiveData<List<ProductView>> getProductViews() {
        return productViews;
    }
}
