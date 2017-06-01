package global.kz.test.data.realm;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import global.kz.test.data.realm.model.Cities;
import global.kz.test.di.ApplicationContext;
import global.kz.test.di.PreferenceInfo;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by root on 6/1/17.
 */

@Singleton
public class AppRealmHelper implements RealmHelper {

    @Inject
    Realm mRealm;

    @Inject
    public AppRealmHelper() {
    }


    @Override
    public void saveCities(Cities cities) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealm(cities);
        });
    }

    @Override
    public RealmResults getCities() {

        return  mRealm.where(Cities.class).findAll();
    }
}
