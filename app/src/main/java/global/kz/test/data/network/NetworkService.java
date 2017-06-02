package global.kz.test.data.network;

import global.kz.test.data.network.model.Weather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 4/18/17.
 */

public interface NetworkService {

    @GET("data/2.5/weather")
    Observable<Weather> getWeather(
            @Query("q") String cityName,
            @Query("appid") String appId
    );

}
