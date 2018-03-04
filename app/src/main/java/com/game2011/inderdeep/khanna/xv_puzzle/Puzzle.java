package com.game2011.inderdeep.khanna.xv_puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by singh on 2018-03-03.
 */

public class Puzzle
{

    public List<Integer> puzzleList;

    public Puzzle()
    {
        puzzleList = new ArrayList<Integer>();
        for (int i = 0 ; i < 16; i++)
        {
            int puzzleValue = getRandomValue();
            puzzleList.add(puzzleValue);
        }
    }

    int getRandomValue()
    {
        int result;
        Random randomGenerator = new Random();
        result = randomGenerator.nextInt(16);
        if (puzzleList.contains(result))
        {
            result = getRandomValue();
        }
        return result;
    }

    int getIndexOfValue(int value)
    {
        return puzzleList.indexOf(value);
    }

    boolean switchEmptyTileWith(int value)
    {
        boolean result = false;
        if(canSwitchEmptyTileWith(value))
        {
            int indexOfReceivedValue = getIndexOfValue(value);
            int indexOfEmptyTile = getIndexOfValue(0);
            puzzleList.set(indexOfReceivedValue,0);
            puzzleList.set(indexOfEmptyTile,value);
            result = true;
        }
        return result;
    }

    boolean canSwitchEmptyTileWith(int value)
    {
        boolean result = false;

        int indexOfEmptyTile = getIndexOfValue(0);

        int[] validIndices = new int[4];
        validIndices[0] = indexOfEmptyTile - 4;
        validIndices[1] = indexOfEmptyTile + 4;
        validIndices[2] = indexOfEmptyTile - 1;
        validIndices[3] = indexOfEmptyTile + 1;

        for (int i = 0 ; i < 4 ; i++)
        {
            int index = validIndices[i];
            if( index >= 0 && index < puzzleList.size() && puzzleList.get(index) == value)
            {
                result = true;
                break;
            }
        }

        return result;
    }

    boolean isPuzzleSolved()
    {
        boolean result = true;

        for(int i = 0 ; i < 15; i++)
        {
            if (puzzleList.get(i) != i+1)
            {
                result = false;
                break;
            }
        }

        return result;
    }

    void generateCloseToWinConditionPuzzle()
    {
        puzzleList = new ArrayList<Integer>();
        for (int i = 0 ; i < 16; i++)
        {
            int puzzleValue = i+1;
            puzzleList.add(puzzleValue);
        }
        puzzleList.set(15,15);
        puzzleList.set(14,0);
    }

}
