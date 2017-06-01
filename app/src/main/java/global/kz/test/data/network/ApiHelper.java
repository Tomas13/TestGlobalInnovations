package global.kz.test.data.network;

import global.kz.test.data.network.model.Envelope;
import global.kz.test.data.network.model.request.RequestEnvelope;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

public interface ApiHelper {

    Observable<Envelope> doAuthorizeOnServer(RequestEnvelope requestEnvelope);

}
