package global.kz.test.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import global.kz.test.ui.choosecity.ChooseCityActivity;

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
    @BindView(R.id.tv_wind)
    TextView tvWind;

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
//        int celsius = (int) (weather.getMain().getTemp() - 273.15F);

        tvDegrees.setText(String.valueOf(weather.getMain().getTemp()));

        tvWind.setText(String.valueOf(weather.getWind().getSpeed()));
        tvPressure.setText(String.valueOf(weather.getMain().getPressure()));

        switch (weather.getWeather().get(0).getDescription()) {
            case "clear sky":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
                break;
            case "scattered clouds":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                break;
            case "broken clouds":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                break;
            case "light intensity shower rain":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                break;

            case "shower rain":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                break;
            case "few clouds":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                break;


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //меню для перехода в активити выбора города
        if (id == R.id.menu_choose_city) {
            startActivity(this, new ChooseCityActivity());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
