package global.kz.test.data.realm;

import global.kz.test.data.realm.model.Cities;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by root on 4/12/17.
 */

public interface RealmHelper {

    void saveCities(Cities cities);

    RealmResults getCities();
}
