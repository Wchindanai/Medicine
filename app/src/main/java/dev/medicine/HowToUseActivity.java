package dev.medicine;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HowToUseActivity extends AppCompatActivity {

    private static final String TAG = "HowToUseActivity";
    private RecyclerView recyclerView;
    private List<CategoryModel> categoryModelList;
    private HowToUseAdapter howToUseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        categoryModelList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getApplicationContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        readCategory();
    }

    private void readCategory() {
        AssetManager assetManager = getAssets();
        try {
            InputStream categoryStream = assetManager.open("category/category.csv");
            InputStreamReader categoryReader = new InputStreamReader(categoryStream);
            CSVReader csvReader = new CSVReader(categoryReader);
            String[] line;
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                CategoryModel categoryModel = new CategoryModel(Integer.parseInt(line[0]), line[1]);
                categoryModelList.add(categoryModel);
            }
            howToUseAdapter = new HowToUseAdapter(this, categoryModelList);
            recyclerView.setAdapter(howToUseAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
