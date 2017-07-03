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

public class activity_herb extends AppCompatActivity implements SearchView.OnQueryTextListener{
    List<HerbModel> herb_list;
    HerbRecyclerAdapter herbRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herb);

        herb_list = new ArrayList<>();

        //Init Recycler View And Set Adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        herbRecyclerAdapter = new HerbRecyclerAdapter(this, herb_list);
        recyclerView.setAdapter(herbRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(getApplicationContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        try {
            readHerbTable();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void readHerbTable() throws UnsupportedEncodingException {
        AssetManager assetManager = getAssets();
        try {
            InputStream herbStream = assetManager.open("herb/herb_table.csv");
            InputStreamReader herbReader = new InputStreamReader(herbStream);
            CSVReader csvReader = new CSVReader(herbReader);
            csvReader.readNext();
            String line[];
            while((line = csvReader.readNext()) != null){
                int id = (int) csvReader.getLinesRead();
                addDrugToObject(id, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addDrugToObject(int id, String[] line) {
        HerbModel herbModel = new HerbModel(id, line[0], line[1], line[2], line[3], line[4]);
        herb_list.add(herbModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_search, menu);
        MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setQueryHint(getString(R.string.search_herb));
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
        ArrayList<HerbModel> newList = new ArrayList<>();
        for(HerbModel herbModel: herb_list){
            String name = herbModel.getName().toLowerCase();
            if(name.contains(newText)){
                newList.add(herbModel);
            }
        }
        herbRecyclerAdapter.setFilter(newList);
        return true;
    }
}
