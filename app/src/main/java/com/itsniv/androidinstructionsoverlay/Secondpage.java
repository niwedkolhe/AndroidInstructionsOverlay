package com.itsniv.androidinstructionsoverlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by it's niv on 18-09-2017.
 */
public class Secondpage extends Activity {
    float x1,y1;
    float x2,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView tv=(TextView)findViewById(R.id.main_text);
        tv.setText("Page Two");
    }
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
// when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
//if left to right sweep event on screen
                if (x1 < x2)
                {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Secondpage.this,MainActivity.class);
                    startActivity(i);
                }
// if right to left sweep event on screen
                if (x1 > x2)
                {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();

                }
// if UP to Down sweep event on screen
                if (y1 < y2)
                {
// Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }
//if Down to UP sweep event on screen
                if (y1 > y2)
                {
// Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }
}
