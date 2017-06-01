package global.kz.test.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import global.kz.test.data.network.model.Envelope;
import global.kz.test.data.network.model.request.RequestEnvelope;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    NetworkService networkService;

    @Override
    public Observable<Envelope> doAuthorizeOnServer(RequestEnvelope requestEnvelope) {
        return networkService.requestStateInfoObs(requestEnvelope);
    }

    @Inject
    public AppApiHelper() {
    }
    //    @Inject
//    public AppApiHelper(ApiHeader apiHeader) {
//        mApiHeader = apiHeader;
//    }
}
