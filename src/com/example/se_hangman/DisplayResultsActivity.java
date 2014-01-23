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
	    startActivity(intent);
	    finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_results);
		
		mainIntent = getIntent();
		int results = mainIntent.getIntExtra("results", 0); 
		String myWord = mainIntent.getStringExtra("word"); 
        final TextView textResults = (TextView) findViewById(R.id.textView1);
        final Button btnAgain = (Button) findViewById(R.id.button1);
        
        if (results == 1) {
        	textResults.setText("Congratulations!\nYou Won!\n\nThe word was:\n" + myWord);
        } else if (results == 2) {
        	textResults.setText("Sorry\nYou Lost...\n\nThe word was:\n" + myWord);
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
