package com.example.shopper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateList extends AppCompatActivity {
    // declare an intent
    Intent intent;


    // declare edite texts fields
    EditText nameEditText;
    EditText storeEditText;
    EditText dateEditText;
    // create Calendar
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Edits
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        storeEditText=(EditText)findViewById(R.id.storeEditText);
        dateEditText =(EditText)findViewById(R.id.dateEditText);
        // Initialize the calendar
        calendar = Calendar.getInstance();
    // Initialize a DatePickerDialogue and register an OnDateSeListener to it
        final DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayofMonth) {
                // set the Calendar year, month, and day to year, month, and day
                // selected in DatePickerDialog
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH,dayofMonth);
                    // call the method that updates date EditText with date set in DatePickerDialog
                    updateDueDate();

            }
        };

        // register an OnCLick listener on the date EditText;
        dateEditText.setOnClickListener(new View.OnClickListener(){
        // this method gets called when the date EditText is clicked
            @Override
            public void onClick(View view) {
                // display DatePickerDialog with current date selected
                new DatePickerDialog(CreateList.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



    }

    public void updateDueDate(){
        // create a SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM--dd", Locale.getDefault());
        // apply SimpleDateFormat to date in Calendar and set it in the date EditText
        dateEditText.setText(simpleDateFormat.format(calendar.getTime()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // get the id of the item that was selected
        switch (item.getItemId()) {
            case R.id.action_home:
                // initializing an intent for the main activity, starting it
                // and returning true
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_create_list:
                // initializing an intent for the create list activity, starting it
                // and returning true
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    public void createList(MenuItem menuItem){

        String name= nameEditText.getText().toString();
        String store= storeEditText.getText().toString();
        String date= dateEditText.getText().toString();
        // trim strings and see if they're equal to empty string
        if(name.trim().equals("")|| store.trim().equals("")|| date.trim().equals("")){
            Toast.makeText(this,"Please enter a name, sotere, and date!", Toast.LENGTH_LONG).show();
        }else{
    // if none of the Strings are empty, display, Shopping list Added
            Toast.makeText(this,"Shopping List Added!", Toast.LENGTH_LONG).show();
        }
    }

}
