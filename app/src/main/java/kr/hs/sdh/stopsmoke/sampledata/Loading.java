package kr.hs.sdh.stopsmoke.sampledata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.hs.sdh.stopsmoke.DBhelper;
import kr.hs.sdh.stopsmoke.MainActivity;
import kr.hs.sdh.stopsmoke.R;

public class Loading extends AppCompatActivity {

    private DBhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        dbhelper = new DBhelper(this);
        dbhelper.open();
        Intent intent = getIntent();

        String many = intent.getExtras().getString("index");

        dbhelper.updatemany(many);
        dbhelper.updateon(1);

        Intent intents = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intents);
        finish();
    }
}
