package global.kz.test.ui.main;

import javax.inject.Inject;

import global.kz.test.data.DataManager;
import global.kz.test.data.network.model.Weather;
import global.kz.test.ui.base.BasePresenter;
import global.kz.test.utils.AppConstants;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 4/12/17.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadWeatherData(String cityName) {

        getMvpView().showLoading();

        Observable<Weather> weatherObservable = getDataManager().getWeather(cityName, AppConstants.APPID);

        weatherObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {

                            getMvpView().showWeatherData(weather);
                            getMvpView().hideLoading();
                        },
                        throwable -> {
                            getMvpView().onErrorToast(throwable.getMessage());
                            getMvpView().hideLoading();

                        });
    }

}
