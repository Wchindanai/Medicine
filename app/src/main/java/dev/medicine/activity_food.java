package dev.medicine;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

public class activity_food extends AppCompatActivity implements SearchView.OnQueryTextListener {
    List<FoodModel> food_list;
    FoodRecyclerAdapter foodRecyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        food_list = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        foodRecyclerAdapter = new FoodRecyclerAdapter(this, food_list);
        recyclerView.setAdapter(foodRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getApplicationContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

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
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setQueryHint(getString(R.string.search_food));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<FoodModel> newList = new ArrayList<>();
        newText = newText.toLowerCase();
        for(FoodModel foodModel: food_list){
            String name = foodModel.getName().toLowerCase();
            if(name.contains(newText)){
                newList.add(foodModel);
            }
        }
        foodRecyclerAdapter.setFilter(newList);
        return true;
    }
}
