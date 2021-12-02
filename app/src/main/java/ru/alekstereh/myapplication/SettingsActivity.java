package ru.alekstereh.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.radiobutton.MaterialRadioButton;


public class SettingsActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "LOGIN";
    public static final String AppTheme = "APP_THEME";
    protected static final int MyCoolCodeStyle = 0;
    protected static final int AppThemeLightCodeStyle = 1;
    protected static final int AppThemeRedCodeStyle = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyGreen));
        setContentView(R.layout.settings_activity);

        initThemeChooser();



    }


    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMyGreen), MyCoolCodeStyle);
        initRadioButton(findViewById(R.id.radioButton_light_theme), AppThemeLightCodeStyle);
        initRadioButton(findViewById(R.id.radioButton_red_theme), AppThemeRedCodeStyle);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(MyCoolCodeStyle))).setChecked(true);
    }


    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            recreate();
        });
    }


    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }


    private int getAppTheme(int codeStyle) {

        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case AppThemeLightCodeStyle:
                return R.style.light_theme;
            case AppThemeRedCodeStyle:
                return R.style.red_theme;
            default:
                return R.style.MyGreen;
        }
    }


}