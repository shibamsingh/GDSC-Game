package com.example.guessthenumber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        performAction();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    private void performAction(){

        TextView level=findViewById(R.id.level);
        TextView randno=findViewById(R.id.randno);
        Button playagain=findViewById(R.id.Playagian);
        TextView condition=findViewById(R.id.condition);
        EditText enterno=findViewById(R.id.enterno);
        Button submit = findViewById(R.id.button);
        TextView attemptText=findViewById(R.id.attemptText);
        TextView attempt=findViewById(R.id.attempt);
        Random rand=new Random();
        int val=rand.nextInt(11);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int attemptleft=Integer.parseInt(attempt.getText().toString());

                String s=enterno.getText().toString();
                if(s.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter a value.", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(s)<0 || Integer.parseInt(s)>10){
                    Toast.makeText(MainActivity.this, "Please enter a value between 0-10", Toast.LENGTH_SHORT).show();
                }
                else {
                    int enteredno = Integer.parseInt(s);
                    attempt.setText(Integer.toString(attemptleft - 1));

                    if (attemptleft > 0) {

                        if(attemptleft==1){
                            randno.setText("Game Over\nThe random no was: " + val);
                            afterGame(enterno, playagain,submit);
                        }

                        if (enteredno == val) {
                            randno.setText("Congratulations!\nYou guessed right Number");
                            afterGame(enterno, playagain,submit);
                        }
                        else if (enteredno < val) {
                            if(attemptleft!=1)
                                randno.setText("You guessed too Low");
                        }
                        else {
                            if(attemptleft!=1)
                                randno.setText("You guessed too High");
                        }
                    }
                }

                enterno.setText("");


                playagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ResetToDefault(randno,enterno, playagain,submit,attempt);
                        performAction();
                    }
                });
            }
        });
    }

    private void afterGame(EditText enterno,Button playagain,Button submit){
        enterno.setEnabled(false);
        playagain.setEnabled(true);
        playagain.setText("Play Again");
        playagain.setBackgroundColor(getResources().getColor(R.color.blue));
        submit.setEnabled(false);
    }

    private void ResetToDefault(TextView randno,EditText enterno,Button playagain,Button submit, TextView attempt){

        randno.setText("");
        enterno.setEnabled(true);
        playagain.setEnabled(false);
        playagain.setText("");
        playagain.setBackgroundColor(getResources().getColor(R.color.white));
        submit.setEnabled(true);
        attempt.setText(Integer.toString(3));
    }

}