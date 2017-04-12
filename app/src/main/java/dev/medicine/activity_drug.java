package dev.medicine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class activity_drug extends AppCompatActivity {

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
        recyclerView.setAdapter(new RecyclerAdapter(this, drug_list));

        DrugModel drug = new DrugModel(1, "Paracetamol", "Eat", "Low", " ", " ");
        drug_list.add(drug);

        drug = new DrugModel(2, "Nogesic", "Eat", "High", "Test", "Test");
        drug_list.add(drug);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_search, menu);
        MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView)item.getActionView();
        return super.onCreateOptionsMenu(menu);
    }
}

