package com.example.se_hangman;

import android.os.Bundle;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.text.InputFilter;

public class GuessActivity extends Activity {

	int State = 1;	// 1 = Default: User needs to enter a word
					// 2 = User has entered a word and needs to guess the letters
	int Status = 0;	// 0 = Game is not finished
					// 1 = User has won the game
					// 2 = User has lost the game
	int numTries = 0;
	String myWord = "";
	char[] myWordArray;
	int lengthOfWord = 0;
	char guessChar = 0;
	String guessWord = "";
	char[] guessWordArray;
	int[] guessCharArray = new int[27];
	String guessCharString = "Letters guessed: ";
	int guessCharInt = 0;

	Intent intentThis = getIntent();
	
	public void sendMessage(View view, int results) {
	    Intent intent = new Intent(this, DisplayResultsActivity.class);
	    intent.putExtra("results", results);
	    System.out.println("starting DisplayResultsActivity");
	    startActivity(intent);
	    finish();
	}
	
	private int checkIfFinished(char[] myWordArray, char[] guessWordArray, int numTries) {
		String myWordString = new String(myWordArray);
		String guessWordString = new String(guessWordArray);
		if (guessWordString.equals(myWordString))
			return 1;
		if (numTries >= 7)
			return 2;
		return 0;
	}
	private void setIcon(ImageView hmIcon, int numTries) {
		if (numTries == 1) {
			hmIcon.setImageResource(R.drawable.hang1);
		} else if (numTries == 2) {
			hmIcon.setImageResource(R.drawable.hang2);
		} else if (numTries == 3) {
			hmIcon.setImageResource(R.drawable.hang3);
		} else if (numTries == 4) {
			hmIcon.setImageResource(R.drawable.hang4);
		} else if (numTries == 5) {
			hmIcon.setImageResource(R.drawable.hang5);
		} else if (numTries == 6) {
			hmIcon.setImageResource(R.drawable.hang6);
		} else if (numTries == 7) {
			hmIcon.setImageResource(R.drawable.hang7);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess);
		final EditText textInput = (EditText) findViewById(R.id.editText1);     
        final Button btn1 = (Button) findViewById(R.id.button1);
        final TextView textWord = (TextView) findViewById(R.id.textView1);
        final TextView textGuessedLetters = (TextView) findViewById(R.id.textView2);
        final TextView textError = (TextView) findViewById(R.id.textView3);
        final ImageView hmIcon = (ImageView) findViewById(R.id.imageView1);  
        
        for (int i=0;i<=26;i++) {
        	guessCharArray[i] = 0;
        }
        
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	switch (State) {
            	case 1:
            		myWord = textInput.getText().toString().toLowerCase(Locale.getDefault());
            		lengthOfWord = myWord.length();
            		if (lengthOfWord == 0) {
            			textError.setText("Error: No word entered");
            			break;
            		}
            		myWordArray = myWord.toCharArray();
            		guessWordArray = new char[myWord.length()];
            		for (int i=0;i<myWord.length();i++) {
            			guessWordArray[i] = 42;
            		}
            		textWord.setText(String.valueOf(guessWordArray));
            		textInput.setText("");
            		textInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
            		btn1.setText("Guess character");
            		State = 2;
            		break;
            	case 2:
            		guessChar = textInput.getText().toString().toLowerCase(Locale.getDefault()).charAt(0);
            		
            		if (guessChar >= 97 && guessChar <= 122) {
            			if (guessCharArray[guessChar-96] == 0) {
            				guessCharArray[guessChar-96] = 1;
            				if (guessCharInt == 0)
            					guessCharString = guessCharString + Character.toString(guessChar);
            				else
            					guessCharString = guessCharString + "," + Character.toString(guessChar);
            				guessCharInt++;
            				int numCorrect = 0;
		            		for (int i=0;i<myWord.length();i++) {
		            			if (myWordArray[i] == guessChar) {
		            				guessWordArray[i] = guessChar;
		            				numCorrect++;
		            			}
		            		}
		            		if (numCorrect == 0) {
		                		numTries++;
		                		setIcon(hmIcon, numTries);
		            		}
		            		textGuessedLetters.setText(guessCharString);
		            		textError.setText("");
            			} else {
            				textError.setText("Already guessed");
            			}
	            		textInput.setText("");
	            		textWord.setText(String.valueOf(guessWordArray));
	            		Status = checkIfFinished(myWordArray, guessWordArray, numTries);
	            	    System.out.println("Status=" + Status);
	            		if (Status == 1) {
	            			sendMessage(v, Status);
	            		}
	            		if (Status == 2) {
	            			sendMessage(v, Status);
	            		}
            		} else {
            			textError.setText("Invalid character");
            		}
            		break;
            	}
            }
        });  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guess, menu);
		return true;
	}

}
