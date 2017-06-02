package global.kz.test.ui.choosecity;

import global.kz.test.data.realm.model.City;
import global.kz.test.di.PerActivity;
import global.kz.test.ui.base.MvpPresenter;

/**
 * Created by root on 4/14/17.
 */

@PerActivity
public interface ChooseCityMvpPresenter<V extends ChooseCityMvpView> extends MvpPresenter<V> {

    void saveCity(City city);

    void loadCities();

    void loadWeather(String cityName);
}
