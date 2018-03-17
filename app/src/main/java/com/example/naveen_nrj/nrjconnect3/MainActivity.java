package com.example.naveen_nrj.nrjconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int active=0;
    boolean isActive = true;
    // active=0 means X, 0 means O;
    int[] state = {2,2,2,2,2,2,2,2,2};
    int [][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View v) {
        ImageView counter = (ImageView) v;
        int tapped = Integer.parseInt(counter.getTag().toString());
        if (state[tapped] == 2 && isActive) {

            state[tapped]=active;

            counter.setTranslationY(-1500f);


            if (active == 0) {
                counter.setImageResource(R.drawable.redx);
                active = 1;

            } else {
                counter.setImageResource(R.drawable.o);
                active = 0;

            }
            counter.animate().translationYBy(1500f).setDuration(200);
             for(int[] win:winningPosition) {

                if(state[win[0]]==state[win[1]] && state[win[1]] == state[win[2]] &&
                        state[win[0]]!=2){
                    isActive=false;

                    String winner="X";

                    if(state[win[0]]==1){
                        winner="O";
                    }
                    //someone has won
                    TextView message = (TextView) findViewById(R.id.winnerMessage);
                    message.setText(winner+" HAS WON!!");
                    LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgainLayout.animate().alpha(1f).setDuration(1000);



                }
            }
        }

    }

    public void playAgain(View v) {
        isActive=true;
        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.animate().alpha(0f).setDuration(10);
        active=0;

        for(int i=0;i<state.length;i++){
            state[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
