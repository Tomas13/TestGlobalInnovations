package global.kz.test;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import javax.inject.Inject;

import global.kz.test.data.DataManager;
import global.kz.test.di.component.ApplicationComponent;
import global.kz.test.di.component.DaggerApplicationComponent;
import global.kz.test.di.module.ApplicationModule;
import io.realm.Realm;

/**
 * Created by root on 4/11/17.
 */

public class MyApp extends Application{


    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
