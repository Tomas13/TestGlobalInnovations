package global.kz.test.data.realm.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by root on 6/1/17.
 */

public class City extends RealmObject{

    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
