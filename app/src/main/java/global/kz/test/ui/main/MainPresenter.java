package global.kz.test.ui.main;

import javax.inject.Inject;

import global.kz.test.data.DataManager;
import global.kz.test.ui.base.BasePresenter;

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
    public void onSortBtnClicked() {
        getMvpView().openScanActivity();
    }

    @Override
    public void onConfigPrinterBtnClicked() {
        getMvpView().onError("config printer");

    }

    @Override
    public void onCloseCellBtnClicked() {
        getMvpView().openCloseCellActivity();
    }
}
