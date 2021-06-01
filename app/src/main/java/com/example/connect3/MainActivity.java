package com.example.connect3;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int current_player = 0;
    int status[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int win_patterns[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean result = false;

    public void click(View view){
        TextView message = (TextView)findViewById(R.id.textView2);
        ImageView image = (ImageView)view.findViewWithTag(view.getTag());
        String player_name = current_player == 0 ? "2B" : "A2";
        int index = Integer.parseInt(image.getTag().toString());

        if(status[index] == -1 && !result){
            if(current_player == 0){
                image.setImageResource(R.drawable.p2b);
            }else{
                image.setImageResource(R.drawable.a2);
            }
            image.setAlpha(1f);

            status[index] = current_player;
            current_player = current_player == 0 ? 1 : 0;

            for(int[] pattern: win_patterns){
                result = status[pattern[0]] == status[pattern[1]] && status[pattern[1]] == status[pattern[2]] && status[pattern[2]] != -1? true : false;
                if(result){
                    message.setText("Winner : " + player_name);
                    break;
                }
            }
        }
    }

    public void reset(View view){
        result = false;
        current_player = 0;
        TextView message = (TextView)findViewById(R.id.textView2);

        message.setText("");
        for(int i = 0; i < status.length; i++){
            int view_id = getResources().getIdentifier("imageView" + i, "id", getPackageName());
            ImageView image = (ImageView)findViewById(view_id);

            status[i] = -1;
            image.setAlpha(0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}