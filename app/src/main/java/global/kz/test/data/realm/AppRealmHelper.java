package global.kz.test.data.realm;

import javax.inject.Inject;
import javax.inject.Singleton;

import global.kz.test.data.realm.model.City;
import io.realm.Realm;
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
    public void saveCities(City city) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealm(city);
        });
    }

    @Override
    public RealmResults getCities() {

        return  mRealm.where(City.class).findAll();
    }
}
