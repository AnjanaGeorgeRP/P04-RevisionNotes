package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNote;
    Button buttonInsertNote,buttonShowList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = (EditText) findViewById(R.id.editTextNote);
        buttonInsertNote = (Button) findViewById(R.id.buttonInsertNote);
        buttonShowList = (Button) findViewById(R.id.buttonShowList);

        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        buttonInsertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the RadioGroup object
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupStars);
                // Get the Id of the selected radio button in the RadioGroup
                int selectedButtonId = rg.getCheckedRadioButtonId();
                int star = 5;
                if(selectedButtonId == R.id.radio1){
                    star = 1;
                }else if(selectedButtonId == R.id.radio2){
                    star = 2;
                }else if(selectedButtonId == R.id.radio3){
                    star = 3;
                }else if(selectedButtonId == R.id.radio4){
                    star = 4;
                }

                String content = editTextNote.getText().toString();

                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertNote(content, star);
                db.close();

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
