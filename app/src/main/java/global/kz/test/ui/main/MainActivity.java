package global.kz.test.ui.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import global.kz.test.R;
import global.kz.test.data.network.model.Weather;
import global.kz.test.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_weather_type)
    TextView tvWeatherType;
    @BindView(R.id.tv_degrees)
    TextView tvDegrees;
    @BindView(R.id.tv_pressure)
    TextView tvPressure;
    @BindView(R.id.ll_pressure)
    LinearLayout llPressure;
    @BindView(R.id.tv_city_title)
    TextView tvCityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        mPresenter.onAttach(MainActivity.this);

        ArrayList<String> cities;

        if (getIntent() != null) {
            cities = (ArrayList<String>) getIntent().getExtras().get("city");
            int pos = (int) getIntent().getExtras().get("position");
//            Toast.makeText(this, "" + cities.get(pos), Toast.LENGTH_SHORT).show();
            String cityName = cities.get(pos);
            tvCityTitle.setText(cityName);
            mPresenter.loadWeatherData(cityName);
        }


    }

    @Override
    public void showWeatherData(Weather weather) {
        tvWeatherType.setText(weather.getWeather().get(0).getDescription());

        //переводим Кельвины в Цельсии
        int celsius = (int) (weather.getMain().getTemp() - 273.15F);

        tvDegrees.setText(String.valueOf(celsius));

        tvPressure.setText(String.valueOf(weather.getMain().getPressure()));

    }

}
