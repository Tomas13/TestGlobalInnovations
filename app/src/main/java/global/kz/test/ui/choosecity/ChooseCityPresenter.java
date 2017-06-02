package global.kz.test.ui.choosecity;

import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import global.kz.test.data.DataManager;
import global.kz.test.data.network.model.Weather;
import global.kz.test.data.realm.model.City;
import global.kz.test.ui.base.BasePresenter;
import global.kz.test.utils.AppConstants;
import io.realm.RealmQuery;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 4/14/17.
 */

public class ChooseCityPresenter<V extends ChooseCityMvpView> extends BasePresenter<V> implements ChooseCityMvpPresenter<V> {

    @Inject
    public ChooseCityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void saveCity(City city) {

        getDataManager().saveCities(city);
    }

    @Override
    public void loadCities() {

        RealmQuery realmQuery = getDataManager().getCities().where();

        //в первый запуск, дефолтные города
        if (realmQuery.findAll().size() == 0) {
            City city = new City();

            String[] citiesArray = {"Astana", "Moscow", "London", "New-York", "Melbourne"};
            for (String cityName : citiesArray) {
                city.setCity(cityName);
                getDataManager().saveCities(city);
            }
        }


        ArrayList<String> citiesArrayList = new ArrayList<>();

        for (int i = 0; i < realmQuery.findAll().size(); i++) {
            City city = (City) realmQuery.findAll().get(i);
            Log.d("Citi", city.getCity());
            citiesArrayList.add(city.getCity());
        }

        getMvpView().showCities(citiesArrayList);
    }


    @Override
    public void loadWeather(String cityName) {

        getMvpView().showLoading();

        Observable<Weather> weatherObservable = getDataManager().getWeather(cityName, AppConstants.APPID);

        weatherObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {

                            getMvpView().showWeatherData(weather);
                            getMvpView().hideLoading();
                        },
                        throwable -> {
                            getMvpView().onErrorToast(throwable.getMessage());
                            getMvpView().hideLoading();

                        });
    }
}

