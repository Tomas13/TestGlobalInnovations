package global.kz.test.data.network;

import global.kz.test.data.network.model.Envelope;
import global.kz.test.data.network.model.request.RequestEnvelope;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by root on 4/18/17.
 */

public interface NetworkService {

    @POST("mobiterminal/Terminal.wsdl")
    @Headers("Content-Type: text/xml")
    Observable<Envelope> requestStateInfoObs(@Body RequestEnvelope requestEnvelope);

}
