package global.kz.test.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import global.kz.test.di.ApplicationContext;
import global.kz.test.di.PreferenceInfo;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    private static final String PREF_KEY_CITY = "PREF_KEY_CITY";


    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

//    @Override
//    public void saveCity(String city) {
//        mPrefs.edit().putString(PREF_KEY_CITY, city).apply();
//    }

//    @Override
//    public Set<String> getCities() {
//        Set<String> arrayList;
//        arrayList = mPrefs.getStringSet(PREF_KEY_CITY, null);
//        return arrayList;
//    }
}
