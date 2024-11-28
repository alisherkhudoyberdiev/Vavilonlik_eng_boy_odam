package uz.khudoyberdiev.vavilonlikengboyodam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "theme_prefs";
    private static final String KEY_IS_NIGHT_MODE = "is_night_mode";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        // TextView'ni olish
        textView = findViewById(R.id.textView);

        // Ilova rejimini sozlash
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isNightMode = preferences.getBoolean(KEY_IS_NIGHT_MODE, false);

        // Faqat o'qish oynasining fonini sozlash
        setNightMode(isNightMode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menyuni yuklash
        getMenuInflater().inflate(R.menu.menu_read, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tungi/kunduz rejimini o'zgartirish
        if (item.getItemId() == R.id.action_toggle_theme) {
            toggleTheme();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleTheme() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isNightMode = preferences.getBoolean(KEY_IS_NIGHT_MODE, false);

        // Yangi rejimni o'zgartirish
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_IS_NIGHT_MODE, !isNightMode);
        editor.apply();

        // O'qish oynasining fonini o'zgartirish
        setNightMode(!isNightMode);
    }

    private void setNightMode(boolean isNightMode) {
        if (isNightMode) {
            // Tungi rejim (qora fon, oq matn)
            textView.setBackgroundColor(getResources().getColor(android.R.color.black));
            textView.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            // Kunduzgi rejim (oq fon, qora matn)
            textView.setBackgroundColor(getResources().getColor(android.R.color.white));
            textView.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}
