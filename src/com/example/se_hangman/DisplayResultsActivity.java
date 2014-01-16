package com.example.se_hangman;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayResultsActivity extends Activity {
	
	Intent mainIntent;
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, GuessActivity.class);
	    String message = "";
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	    finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_results);
		
		mainIntent = getIntent();
		int results = mainIntent.getIntExtra("results", 0); //mainIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final TextView textResults = (TextView) findViewById(R.id.textView1);
        final Button btnAgain = (Button) findViewById(R.id.button1);
        
        if (results == 1) {
        	textResults.setText("Congratulations!\nYou Won!");
        } else if (results == 2) {
        	textResults.setText("Sorry\nYou Lost...");
        } else {
        	textResults.setText("There was an error!");
        }

        btnAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	sendMessage(v);
            
            }
        });
            
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_results, menu);
		return true;
	}

}
