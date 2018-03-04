package com.game2011.inderdeep.khanna.xv_puzzle;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayButtonClicked(View v)
    {
        Intent openPlayActivity = new Intent(this,PlayActivity.class);
        startActivity(openPlayActivity);
    }

    public void onQuitButtonClicked(View v)
    {
        ActivityCompat.finishAffinity(this);
        finish();
    }

    public void onInfoButtonClicked(View v)
    {
        Intent infoPlayActivity = new Intent(this,InfoActivity.class);
        startActivity(infoPlayActivity);
    }
}
