package global.kz.test.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import global.kz.test.data.network.model.Weather;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    NetworkService networkService;

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<Weather> getWeather(String cityName, String appId, String units) {
        return networkService.getWeather(cityName, appId, units);
    }
}
