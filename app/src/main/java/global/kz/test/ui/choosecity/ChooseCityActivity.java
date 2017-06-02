package global.kz.test.ui.choosecity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import global.kz.test.R;
import global.kz.test.data.realm.model.City;
import global.kz.test.ui.base.BaseActivity;
import global.kz.test.ui.main.MainActivity;

public class ChooseCityActivity extends BaseActivity implements ChooseCityMvpView {

    @Inject
    ChooseCityMvpPresenter<ChooseCityMvpView> presenter;
    @BindView(R.id.lv_cities)
    ListView lvCities;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @BindArray(R.array.default_cities)
    String[] default_cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        presenter.onAttach(ChooseCityActivity.this);

        presenter.loadCities();

    }

    private ArrayList<String> new_cities = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;


    @Override
    public void showCities(ArrayList<String> cities) {

        for (String city : cities) {
            new_cities.add(city);
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new_cities);
        lvCities.setAdapter(arrayAdapter);
        lvCities.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("city", new_cities);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    @OnClick(R.id.floatingActionButton)
    public void onViewClicked() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_dialog);
        EditText editText = (EditText) dialog.findViewById(R.id.et_city);
        Button button = (Button) dialog.findViewById(R.id.btn_dialog_ok);
        button.setOnClickListener(v -> {
            dialog.dismiss();
            City city = new City();
            city.setCity(editText.getText().toString());

            presenter.saveCity(city);

            new_cities.add(city.getCity());
            arrayAdapter.notifyDataSetChanged();
        });

        dialog.show();
    }

}
