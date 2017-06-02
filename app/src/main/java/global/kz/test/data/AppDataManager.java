package global.kz.test.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import global.kz.test.data.network.ApiHelper;
import global.kz.test.data.network.model.Weather;
import global.kz.test.data.prefs.PreferencesHelper;
import global.kz.test.data.realm.RealmHelper;
import global.kz.test.data.realm.model.City;
import global.kz.test.di.ApplicationContext;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private final RealmHelper mRealmHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper,
                          RealmHelper realmHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mRealmHelper = realmHelper;
    }


    public Context getmContext() {
        return mContext;
    }


    public PreferencesHelper getmPreferencesHelper() {
        return mPreferencesHelper;
    }

//    @Override
//    public void saveCity(String city) {
//        mPreferencesHelper.saveCity(city);
//    }

//    @Override
//    public Set<String> getCities() {
//        return mPreferencesHelper.getCities();
//    }

    @Override
    public void saveCities(City city) {
        mRealmHelper.saveCities(city);
    }

    @Override
    public RealmResults getCities() {
        return mRealmHelper.getCities();
    }

    @Override
    public void removeRealmItem(int position) {
        mRealmHelper.removeRealmItem(position);
    }

    @Override
    public Observable<Weather> getWeather(String cityName, String appId, String units) {
        return mApiHelper.getWeather(cityName, appId, units);
    }
}
