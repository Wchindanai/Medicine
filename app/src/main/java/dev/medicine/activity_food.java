package dev.medicine;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class activity_food extends AppCompatActivity {
    List<FoodModel> food_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        food_list = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new FoodRecyclerAdapter(this, food_list));

        try {
            readFoodTable();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void readFoodTable() throws UnsupportedEncodingException {
        AssetManager assetManager = getAssets();
        try {
            InputStream foodStream = assetManager.open("food/food_table.csv");
            InputStreamReader foodReader = new InputStreamReader(foodStream);
            CSVReader csvReader = new CSVReader(foodReader);
            csvReader.readNext();
            String line[];
            while((line = csvReader.readNext()) != null){
                int id = (int) csvReader.getLinesRead();
                addFoodToObject(id, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFoodToObject(int id, String[] line) {
        FoodModel food = new FoodModel(id, line[0], line[1], line[2], line[3], line[4]);
        food_list.add(food);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_search, menu);
        MenuItem item = menu.findItem(R.id.search_bar);

        return super.onCreateOptionsMenu(menu);
    }
}
