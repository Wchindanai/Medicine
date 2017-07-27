package dev.medicine;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class activity_drug extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String TAG = "Activity Drug";

    activity_drug activity_drug;

    RecyclerAdapter recyclerAdapter;
    //Setting Model Drug
    private List<DrugModel> drug_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        drug_list = new ArrayList<>();

        //Init Recycler View And Set Adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerAdapter = new RecyclerAdapter(this, drug_list);
        recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getApplicationContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        try {
            readDrugTable();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    //This Function Reading File from csv then make to object in recycler View
    private void readDrugTable() throws UnsupportedEncodingException {
        AssetManager assetManager = getAssets();
        try {
            InputStream drugStream = assetManager.open("drug/medicine.csv");
            InputStreamReader drugReader = new InputStreamReader(drugStream);
            CSVReader csvReader = new CSVReader(drugReader);
            String[] line;
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                int lineNo = (int) csvReader.getLinesRead();
                addDrugToObject(lineNo, line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDrugToObject(int lineNo, String[] line) {
        DrugModel drug = new DrugModel(Integer.parseInt(line[0]), line[1], line[2], line[3], line[4]);
        drug_list.add(drug);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_search, menu);
        MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setQueryHint(getString(R.string.search_drug));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<DrugModel> newList = new ArrayList<>();
        for(DrugModel drugModel: drug_list){
            String name = drugModel.getName().toLowerCase();
            if(name.contains(newText)){
                newList.add(drugModel);
            }
        }
        recyclerAdapter.setFilter(newList);
        return true;
    }
}

