package com.game2011.inderdeep.khanna.xv_puzzle;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;

public class PlayActivity extends AppCompatActivity {

    Puzzle puzzle;

    HashMap<String,Button> mapOfButtons;

    private int numberOfMoves = 0;
    private TextView movesTextView;

    private static int BUTTON_IDS[] = {
            R.id.box0,
            R.id.box1,
            R.id.box2,
            R.id.box3,
            R.id.box4,
            R.id.box5,
            R.id.box6,
            R.id.box7,
            R.id.box8,
            R.id.box9,
            R.id.box10,
            R.id.box11,
            R.id.box12,
            R.id.box13,
            R.id.box14,
            R.id.box15
        };

    private static int DRAWABLE_IDS[] = {
            R.drawable.box0,
            R.drawable.box1,
            R.drawable.box2,
            R.drawable.box3,
            R.drawable.box4,
            R.drawable.box5,
            R.drawable.box6,
            R.drawable.box7,
            R.drawable.box8,
            R.drawable.box9,
            R.drawable.box10,
            R.drawable.box11,
            R.drawable.box12,
            R.drawable.box13,
            R.drawable.box14,
            R.drawable.box15
    };

    private static int MAX_MOVES = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        puzzle = new Puzzle();
        mapOfButtons = new HashMap<>();

        for(int i = 0 ; i < 16; i ++)
        {
            Button button = findViewById(BUTTON_IDS[i]);
            mapOfButtons.put("box"+Integer.toString(i),button);
        }

        //TODO: Uncomment to create easily winnable Puzzle.
//        puzzle.generateCloseToWinConditionPuzzle();

        movesTextView = findViewById(R.id.movesTextView);

        showPuzzleOnUI();

    }

    void showPuzzleOnUI()
    {
        for (int i = 0 ; i < puzzle.puzzleList.size(); i++)
        {
            int backgroundIndexValue = puzzle.puzzleList.get(i);
            Drawable background = ContextCompat.getDrawable(this,DRAWABLE_IDS[backgroundIndexValue]);
            mapOfButtons.get("box"+Integer.toString(i)).setBackground(background);
            mapOfButtons.get("box"+Integer.toString(i)).setTag(Integer.toString(backgroundIndexValue));
        }
    }

    void openLooseActivity()
    {
        Intent openWinLooseActivity = new Intent(this,WinLooseActivity.class);
        openWinLooseActivity.putExtra("result","You lost!\nOut of turns!");
        startActivity(openWinLooseActivity);
    }

    void openWinActivity()
    {
        Intent openWinLooseActivity = new Intent(this,WinLooseActivity.class);
        openWinLooseActivity.putExtra("result","You won!");
        startActivity(openWinLooseActivity);
    }

    public void onBoxClicked(View view)
    {
        Button button = (Button) view;
        int selectedButtonValue = Integer.parseInt(button.getTag().toString());
        boolean switchMoveSuccess = puzzle.switchEmptyTileWith(selectedButtonValue);
        if(switchMoveSuccess)
        {
            numberOfMoves++;
            movesTextView.setText(String.format(Locale.CANADA,"%d",numberOfMoves));
            showPuzzleOnUI();

            if (numberOfMoves >= MAX_MOVES)
            {
                openLooseActivity();
            }

            if(puzzle.isPuzzleSolved())
            {
                openWinActivity();
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }

        builder.setTitle("Quit Game!")
                .setMessage("Are you sure you want to quit the current game?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        PlayActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
