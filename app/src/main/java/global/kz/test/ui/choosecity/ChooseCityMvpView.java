package global.kz.test.ui.choosecity;

import java.util.ArrayList;

import global.kz.test.ui.base.MvpView;

/**
 * Created by root on 4/14/17.
 */

public interface ChooseCityMvpView extends MvpView {

    void openPrintActivity();

    void showCities(ArrayList<String> cities);
}
