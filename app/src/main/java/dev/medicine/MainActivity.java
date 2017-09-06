package dev.medicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton drug_btn = (ImageButton)findViewById(R.id.icon_drug);
        ImageButton herb_btn = (ImageButton)findViewById(R.id.icon_herb);
        ImageButton food_btn = (ImageButton)findViewById(R.id.icon_food);


        drug_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_drug.class));
            }
        });
        herb_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_herb.class));
            }
        });
        food_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_food.class));
            }
        });

    }

}
