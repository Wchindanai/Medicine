package dev.medicine;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_launch_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        ImageButton start_btn = (ImageButton)findViewById(R.id.icon_start);
        TextView applicationDetail = (TextView)findViewById(R.id.detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            applicationDetail.setText(Html.fromHtml(getString(R.string.application_detail),Html.FROM_HTML_MODE_COMPACT));
        }else {
            applicationDetail.setText(Html.fromHtml(getString(R.string.application_detail)));
        }

        start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }

        });
    }
}
