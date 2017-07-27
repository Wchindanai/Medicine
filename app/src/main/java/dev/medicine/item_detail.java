package dev.medicine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class item_detail extends AppCompatActivity {

    private TextView title, risk, how, reference;

    private static final String TAG = "item_detail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        title = (TextView) findViewById(R.id.title);
        risk = (TextView) findViewById(R.id.risk);
        how = (TextView) findViewById(R.id.how);
        reference = (TextView) findViewById(R.id.reference);

        Bundle bundle = getIntent().getExtras();
        String titleText = bundle.getString("title");
        String riskText = bundle.getString("risk");
        String howText = bundle.getString("howTo");
        String referenceText = bundle.getString("reference");
        Log.i(TAG, "onCreate: "+ referenceText);


        title.setText(titleText);
        risk.setText(riskText);
        how.setText(howText);
        reference.setText(referenceText);
    }
}
