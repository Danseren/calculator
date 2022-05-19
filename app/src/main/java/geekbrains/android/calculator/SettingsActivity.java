package geekbrains.android.calculator;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener  {

    private RadioButton rb_Theme01, rb_Theme02, rb_Theme03;
    private Button btn_back;

    private static final String appTheme = "APP_THEME";
    private static String NameSharedPreference = "LOGIN";
    static final String THEME_KEY = "THEME_KEY";

    private static final int Theme01 = 0;
    private static final int Theme02 = 1;
    private static final int Theme03 = 2;

    protected String currentTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyCoolStyle));
        setContentView(R.layout.settings_layout);

        innitViews();
    }

    private void innitViews() {

        rb_Theme01 = findViewById(R.id.rb_Theme01);
        rb_Theme02 = findViewById(R.id.rb_Theme02);
        rb_Theme03 = findViewById(R.id.rb_Theme03);
        btn_back = findViewById(R.id.btn_back);

        initOnClickListeners();
    }

    private void initOnClickListeners() {

        rb_Theme01.setOnClickListener(this);
        rb_Theme02.setOnClickListener(this);
        rb_Theme03.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent themeIntent = new Intent(SettingsActivity.this, MainActivity.class);

        switch (view.getId()) {

            case R.id.rb_Theme01:
                Toast.makeText(SettingsActivity.this, "Theme01", Toast.LENGTH_SHORT).show();
                setAppTheme(Theme01);
                recreate();
                break;
            case R.id.rb_Theme02:
                Toast.makeText(SettingsActivity.this, "Theme02", Toast.LENGTH_SHORT).show();
                setAppTheme(Theme02);
                recreate();
                break;
            case R.id.rb_Theme03:
                Toast.makeText(SettingsActivity.this, "Theme03", Toast.LENGTH_SHORT).show();
                setAppTheme(Theme03);
                recreate();
                break;
            case R.id.btn_back:
                currentTheme = String.valueOf(getCodeStyle(R.style.MyCoolStyle));
                themeIntent.putExtra(THEME_KEY, currentTheme);
                startActivity(themeIntent);
                break;
        }
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(appTheme, codeStyle);
    }

    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(appTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case Theme01:
                return R.style.AppTheme;
            case Theme02:
                return R.style.AppThemeDark;
            case Theme03:
                return R.style.AppThemeLight;
            default:
                return R.style.MyCoolStyle;
        }
    }

}
