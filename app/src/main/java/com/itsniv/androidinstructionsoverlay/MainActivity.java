package com.itsniv.androidinstructionsoverlay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    float x1,y1;
    float x2,y2;
    private static final String PREF_FIRSTLAUNCH_HELP = "helpDisplayed";
    private boolean helpDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showHelpForFirstLaunch();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
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
                }
// if right to left sweep event on screen
                if (x1 > x2)
                {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this,Secondpage.class);
                    startActivity(i);
                }
// if UP to Down sweep event on screen
                if (y1 < y2)
                {
//Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_help) {
            showHelp();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showHelpForFirstLaunch() {
        if (helpDisplayed)
            return;
        helpDisplayed = getPreferenceValue(PREF_FIRSTLAUNCH_HELP, false);
        if (!helpDisplayed) {
            savePreference(PREF_FIRSTLAUNCH_HELP, true);
            showHelp();
        }
    }
    private boolean getPreferenceValue(String key, boolean defaultValue) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }
    private void savePreference(String key, boolean value) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
        private void showHelp() {
            final View instructionsContainer = findViewById(R.id.container_help);
            instructionsContainer.setVisibility(View.VISIBLE);
            instructionsContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    instructionsContainer.setVisibility(View.INVISIBLE);
                }
            });
        }
}