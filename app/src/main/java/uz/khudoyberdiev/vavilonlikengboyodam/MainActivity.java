package uz.khudoyberdiev.vavilonlikengboyodam;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();

        // contents.txt faylini assets-dan o'qish
        readContentsFile();

        // ListView uchun adapter o'rnatish
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itemList
        );
        listView.setAdapter(adapter);

        // ListView elementiga bosilganda
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = itemList.get(position);

            // Bosilgan elementga mos fayl nomi
            String fileName = "part" + (position + 1) + ".txt";

            // ReadActivity-ni ishga tushirish
            Intent intent = new Intent(MainActivity.this, ReadActivity.class);
            intent.putExtra("fileName", fileName);
            startActivity(intent);
        });
    }

    private void readContentsFile() {
        try {
            AssetManager assetManager = getAssets();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(assetManager.open("contents.txt"))
            );
            String line;
            while ((line = reader.readLine()) != null) {
                itemList.add(line);
            }
            reader.close();
        } catch (Exception e) {
            Toast.makeText(this, "contents.txt faylini o'qib bo'lmadi!", Toast.LENGTH_SHORT).show();
        }
    }
}
