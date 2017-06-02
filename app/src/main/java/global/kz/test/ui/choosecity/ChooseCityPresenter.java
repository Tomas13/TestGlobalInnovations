package global.kz.test.ui.choosecity;

import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import global.kz.test.data.DataManager;
import global.kz.test.data.realm.model.City;
import global.kz.test.ui.base.BasePresenter;
import io.realm.RealmQuery;

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
//        Set<String> cities = getDataManager().getCities();
//        if (cities!=null)
//            getMvpView().onErrorToast(cities.toString());
    }


}
