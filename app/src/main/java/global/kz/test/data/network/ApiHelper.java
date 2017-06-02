package global.kz.test.data.network;

import global.kz.test.data.network.model.Weather;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

public interface ApiHelper {

    Observable<Weather> getWeather(String cityName, String appId);

}
