package global.kz.test.ui.choosecity;

import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;

import global.kz.test.data.DataManager;
import global.kz.test.data.realm.model.Cities;
import global.kz.test.ui.base.BasePresenter;
import io.realm.RealmQuery;

/**
 * Created by root on 4/14/17.
 */

public class ChooseCityPresenter<V extends ChooseCityMvpView> extends BasePresenter<V> implements ChooseCityMvpPresenter<V>{

    @Inject
    public ChooseCityPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void saveCity(Cities cities) {

//        getDataManager().saveCity(city);
        getMvpView().openPrintActivity();

        getDataManager().saveCities(cities);
    }

    @Override
    public void loadCities() {

        RealmQuery realmQuery = getDataManager().getCities().where();

        ArrayList<String> citiesArrayList = new ArrayList<>();

        for (int i = 0; i < realmQuery.findAll().size(); i++) {
            Cities cities = (Cities) realmQuery.findAll().get(i);
            Log.d("Citi", cities.getCity());
            citiesArrayList.add(cities.getCity());
        }


        getMvpView().showCities(citiesArrayList);
//        Set<String> cities = getDataManager().getCities();
//        if (cities!=null)
//            getMvpView().onErrorToast(cities.toString());
    }


}
