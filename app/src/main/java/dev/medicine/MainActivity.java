package dev.medicine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton drug_btn = (ImageButton)findViewById(R.id.icon_drug);
    private ImageButton herb_btn = (ImageButton)findViewById(R.id.icon_herb);
    private ImageButton food_btn = (ImageButton)findViewById(R.id.icon_food);
    private ImageButton help_btn = (ImageButton)findViewById(R.id.icon_help);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drug_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_drug);
            }
        });
        herb_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_herb);
            }
        });
        food_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_food);
            }
        });
        help_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_help);
            }
        });
    }

}
