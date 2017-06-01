package global.kz.test.ui.choosecity;

import global.kz.test.data.realm.model.Cities;
import global.kz.test.di.PerActivity;
import global.kz.test.ui.base.MvpPresenter;

/**
 * Created by root on 4/14/17.
 */

@PerActivity
public interface ChooseCityMvpPresenter<V extends ChooseCityMvpView> extends MvpPresenter<V> {

    void saveCity(Cities cities);

    void loadCities();
}
