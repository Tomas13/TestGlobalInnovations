/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package global.kz.test.di.module;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import global.kz.test.data.AppDataManager;
import global.kz.test.data.DataManager;
import global.kz.test.data.network.ApiHelper;
import global.kz.test.data.network.AppApiHelper;
import global.kz.test.data.network.NetworkService;
import global.kz.test.data.prefs.AppPreferencesHelper;
import global.kz.test.data.prefs.PreferencesHelper;
import global.kz.test.data.realm.AppRealmHelper;
import global.kz.test.data.realm.RealmHelper;
import global.kz.test.di.ApplicationContext;
import global.kz.test.di.PreferenceInfo;
import global.kz.test.utils.AppConstants;
import io.realm.Realm;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static global.kz.test.utils.AppConstants.BASE_URL;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

 /*   @Provides
    @ApplicationContext
    static RealmConfiguration provideRealmConfiguration() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        if(BuildConfig.DEBUG) { builder = builder.deleteRealmIfMigrationNeeded(); }
        return builder.build();
    }
*/

    @Provides
    @Singleton
    static Realm provideRealm() {
        return Realm.getDefaultInstance();
    }


    @Provides
    @Singleton
    RealmHelper provideRealmHelper(AppRealmHelper appRealmHelper) {
        return appRealmHelper;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addNetworkInterceptor(new StethoInterceptor());
        client.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);
        client.cache(cache);
        return client.build();
    }


    @Provides
    @Singleton
    NetworkService provideNetworkService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        
//                .baseUrl("http://172.30.223.25:8088/mobiterminal/Terminal.wsdl/")
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();

        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

}
