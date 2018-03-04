package com.game2011.inderdeep.khanna.xv_puzzle;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinLooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        TextView resultTextView = findViewById(R.id.resultTextView);

        String resultString = getIntent().getExtras().getString("result");
        resultString = resultString != null ? resultString : "Something Went Wrong!";

        resultTextView.setText(resultString);
    }

    public void onMainMenuClicked(View view)
    {
        Intent openMainMenu = new Intent(this,MainActivity.class);
        startActivity(openMainMenu);
    }

    public void onQuitClicked(View view)
    {
        ActivityCompat.finishAffinity(this);
        finish();
    }
}
