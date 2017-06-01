package global.kz.test.data;

import android.content.Context;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import global.kz.test.data.network.ApiHelper;
import global.kz.test.data.network.model.Envelope;
import global.kz.test.data.network.model.request.RequestEnvelope;
import global.kz.test.data.prefs.PreferencesHelper;
import global.kz.test.data.realm.RealmHelper;
import global.kz.test.data.realm.model.Cities;
import global.kz.test.di.ApplicationContext;
import io.realm.RealmResults;

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

    @Override
    public rx.Observable<Envelope> doAuthorizeOnServer(RequestEnvelope requestEnvelope) {
        return mApiHelper.doAuthorizeOnServer(requestEnvelope);
    }

    @Override
    public void saveCity(String city) {
        mPreferencesHelper.saveCity(city);
    }

//    @Override
//    public Set<String> getCities() {
//        return mPreferencesHelper.getCities();
//    }

    @Override
    public void saveCities(Cities cities) {
        mRealmHelper.saveCities(cities);
    }

    @Override
    public RealmResults getCities() {
        return mRealmHelper.getCities();
    }
}
