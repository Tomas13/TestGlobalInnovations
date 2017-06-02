package global.kz.test.data.realm;

import global.kz.test.data.realm.model.City;
import io.realm.RealmResults;

/**
 * Created by root on 4/12/17.
 */

public interface RealmHelper {

    void saveCities(City city);

    RealmResults getCities();

    void removeRealmItem(int position);
}
