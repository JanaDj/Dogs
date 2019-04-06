package com.example.meangirl.dogs.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.meangirl.dogs.R;
import com.example.meangirl.dogs.SQLite.Contract;
import com.example.meangirl.dogs.SQLite.DatabaseHelper;

import java.util.ArrayList;

public class DogPicker extends AppCompatActivity {
    Button nameSearchBtn, searchBtn;
    RadioGroup sizeRG, coatRG, energyRG;
    RadioButton sizeSmallRB, sizeMediumRB, sizeLargeRB, coatShortRB, coatMediumRB, coatLongRB, energyLowRB, energyMediumRB, energyHighRB, helpRB;
    EditText nameET;
    ArrayList<String> dogsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_picker_view);
        connectXML();

        /**
         * OnClick listener for the name search with search logic implemented
         */
        nameSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchName = nameET.getText().toString();
                searchName = searchName.trim();
                //resetting dogsList in case user clicked back
                dogsList = new ArrayList<>();

                if(!TextUtils.isEmpty(searchName)) {
                    String searchQuery = "SELECT " + Contract.BreedsSchema.FIELD_NAME + " FROM " + Contract.BreedsSchema.TABLE_NAME +
                            " WHERE " + Contract.BreedsSchema.FIELD_NAME + " LIKE '%" +searchName + "%'";
                    DatabaseHelper dbHelper = new DatabaseHelper(DogPicker.this);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor c = db.rawQuery(searchQuery,null);
                    if(c.moveToFirst()) {
                        do {
                            // Passing values
                            dogsList.add(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_NAME)));
                        } while(c.moveToNext());
                        Intent intent = new Intent(getBaseContext(), BreedsList.class);
                        intent.putExtra("dogsList", dogsList);
                        startActivity(intent);
                    }
                    else {
                        createAlertMsg("Woops! Looks like no dogs were found for your search, please try again with another name or consider adding the dog yourself from the home screen.");
                    }
                    c.close();
                    db.close();
                }
                else {
                    createAlertMsg("Please enter a dog name and try the search again.");
                }
                //resetting the value
                nameET.setText("");
            }
        });

        /**
         * Onclick listener for the search button, with search logic implemented
         */
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resetting value for dogsList in case user clicked the back button to come back from results
                dogsList = new ArrayList<>();
                String searchConditions = "";
                //add size
                if(sizeRG.getCheckedRadioButtonId() != -1) {
                    helpRB = findViewById(sizeRG.getCheckedRadioButtonId()); //we get the selected rb for size
                    searchConditions = "SELECT " + Contract.BreedsSchema.FIELD_NAME + " FROM " + Contract.BreedsSchema.TABLE_NAME +
                          " WHERE " + Contract.BreedsSchema.FIELD_SIZE + " = '" +  (String) helpRB.getText() + "'";
                }
                //add coat
                if(coatRG.getCheckedRadioButtonId() != -1) {
                    helpRB = findViewById(coatRG.getCheckedRadioButtonId()); //we get the selected rb for size
                    if(searchConditions != "") {
                        searchConditions += " AND " + Contract.BreedsSchema.FIELD_COAT + " = '" + (String) helpRB.getText() + "'";
                    }
                    else searchConditions = "SELECT " + Contract.BreedsSchema.FIELD_NAME + " FROM " + Contract.BreedsSchema.TABLE_NAME +
                            " WHERE " + Contract.BreedsSchema.FIELD_COAT + " = '" +  (String) helpRB.getText() + "'";
                }
                //add energy
                if(energyRG.getCheckedRadioButtonId() != -1) {
                    helpRB = findViewById(energyRG.getCheckedRadioButtonId()); //we get the selected rb for size
                    if(searchConditions != "") {
                        searchConditions += " AND " + Contract.BreedsSchema.FIELD_ENERGY + " = '" + (String) helpRB.getText() + "'";
                    }
                    else searchConditions = "SELECT " + Contract.BreedsSchema.FIELD_NAME + " FROM " + Contract.BreedsSchema.TABLE_NAME +
                            " WHERE " + Contract.BreedsSchema.FIELD_ENERGY + " = '" +  (String) helpRB.getText() + "'";
                }
                //running query
                if(searchConditions != "") {
                    DatabaseHelper dbHelper = new DatabaseHelper(DogPicker.this);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor c = db.rawQuery(searchConditions, null);
                    ArrayList<String> dogsList = new ArrayList<>();

                    if (c.moveToFirst()) {
                        do {
                            // Passing values
                            dogsList.add(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_NAME)));
                        } while (c.moveToNext());
                        Intent intent = new Intent(getBaseContext(), BreedsList.class);
                        intent.putExtra("dogsList", dogsList);
                        startActivity(intent);
                    } else {
                        createAlertMsg("Woops! Looks like no dogs were found for your search, please try again with some other properties or consider adding the dog yourself from the home screen.");
                    }
                    c.close();
                    db.close();
                }
                 else {
                    createAlertMsg("Woops! Looks like you have not entered any properties. Please select some properties and try again.");
                }
                //reset properties
                sizeRG.clearCheck();
                coatRG.clearCheck();
                energyRG.clearCheck();
            }
        });
    }
    /**
     * Method to connect java code with XML
     */
    void connectXML(){

        //buttons
        nameSearchBtn = findViewById(R.id.nameSearchBtn);
        searchBtn = findViewById(R.id.searchBTn);
        //radio groups
        sizeRG = findViewById(R.id.sizeRBPicker);
        coatRG = findViewById(R.id.coatRBPicker);
        energyRG = findViewById(R.id.energyRBPicker);
        //Radio buttons
        sizeSmallRB = findViewById(R.id.sizeSmallRB);
        sizeMediumRB = findViewById(R.id.sMediumRB);
        sizeLargeRB = findViewById(R.id.sLargeRB);
        coatShortRB = findViewById(R.id.cShortRB);
        coatMediumRB = findViewById(R.id.cMediumRB);
        coatLongRB = findViewById(R.id.cLongRB);
        energyLowRB = findViewById(R.id.eLowRB);
        energyMediumRB = findViewById(R.id.eMediumRB);
        energyHighRB = findViewById(R.id.eHighRB);
        //edit text
        nameET = findViewById(R.id.nameETPicker);
    }

    /**
     * Method that creates an alert message with OK button, and assigns the alert the string that is passed
     * @param alertMsg, String, message that will be displayed in the alert
     */
    void createAlertMsg(String alertMsg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DogPicker.this);
        builder1.setMessage(alertMsg);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
