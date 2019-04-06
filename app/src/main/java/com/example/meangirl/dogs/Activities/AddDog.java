package com.example.meangirl.dogs.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.meangirl.dogs.FCIData;
import com.example.meangirl.dogs.R;
import com.example.meangirl.dogs.SQLite.Contract;
import com.example.meangirl.dogs.SQLite.DatabaseHelper;

import java.util.ArrayList;

public class AddDog extends Activity {
    Spinner groupSpinner, sectionSpinner;
    RadioGroup sizeRG, coatRG, energyRG;
    RadioButton sizeSmallRB, sizeMediumRB, sizeLargeRB, coatShortRB, coatMediumRB, coatLongRB, energyLowRB, energyMediumRB, energyHighRB, helpRB;
    Button addDogBtn;
    EditText breedNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog);
        connectXML();
        /**
         * On click listner for the addDog button. Dog is added to the database if all fields have been entered correctly
         * If some fields have been left out, alert message is displayed for the user
         */
        addDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //collect the values from each section if they are selected
                if (groupSpinner.getSelectedItem() != null && sectionSpinner.getSelectedItem() != null && !TextUtils.isEmpty(breedNameET.getText()) && sizeRG.getCheckedRadioButtonId() != -1 && coatRG.getCheckedRadioButtonId() != -1 && energyRG.getCheckedRadioButtonId() != -1) {
                    String group = (String) groupSpinner.getSelectedItem();
                    String section = (String) sectionSpinner.getSelectedItem();
                    Editable breedName = breedNameET.getText();
                    helpRB = findViewById(sizeRG.getCheckedRadioButtonId()); //we get the selected rb for size
                    String size = (String) helpRB.getText();
                    helpRB = findViewById(coatRG.getCheckedRadioButtonId()); //we get the selected rb for coat
                    String coat = (String) helpRB.getText();
                    helpRB = findViewById(energyRG.getCheckedRadioButtonId()); //we get the selected rb for energy
                    String energy = (String) helpRB.getText();

                    addDog(group, section, breedName.toString(), size, coat, energy);

                }
                //if some of the values are not selected, alert will be shown to warn the user to fill in all the fields
                else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddDog.this);
                    builder1.setMessage("Please fill in all the fields in order to add a Dog");
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
        });


        /**
         * Groups spinner event listener that will get triggered when item from the list is selected
         */
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sectionSpinner.setEnabled(true);
                switch (i) {
                    case 0:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup1());
                        break;
                    case 1:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup2());
                        break;
                    case 2:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup3());
                        break;
                    case 3:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup4());
                        break;
                    case 4:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup5());
                        break;
                    case 5:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup6());
                        break;
                    case 6:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup7());
                        break;
                    case 7:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup8());
                        break;
                    case 8:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup9());
                        break;
                    case 9:
                        setSectionSpinnerValue((ArrayList<String>) FCIData.getInstance().getGroup10());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    /**
     * Method to connect xml to java
     */
    private void connectXML() {
        //spinners
        groupSpinner = findViewById(R.id.groupSpinner);
        sectionSpinner = findViewById(R.id.sectionSpinner);
        //radio groups
        sizeRG = findViewById(R.id.sizeRG);
        coatRG = findViewById(R.id.coatRG);
        energyRG = findViewById(R.id.energyRG);
        //Radio buttons
        sizeSmallRB = findViewById(R.id.sizeSmallRB);
        sizeMediumRB = findViewById(R.id.sizeMediumRB);
        sizeLargeRB = findViewById(R.id.sizeLargeRB);
        coatShortRB = findViewById(R.id.coatShortRB);
        coatMediumRB = findViewById(R.id.coatMediumRB);
        coatLongRB = findViewById(R.id.coatLongRB);
        energyLowRB = findViewById(R.id.energyLowRB);
        energyMediumRB = findViewById(R.id.energyMediumRB);
        energyHighRB = findViewById(R.id.energyHighRB);
        //buttons
        addDogBtn = findViewById(R.id.addDogBtn);
        //edit text
        breedNameET = findViewById(R.id.breedNameET);
        //set data to group spinner:
        sectionSpinner.setEnabled(false); //section spinner is disabled until group is selected (might not be relative since they have default values)
        //data is assigned to the groups spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,FCIData.getInstance().getGroupsData());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(dataAdapter);

    }
    /**
     * Method that takes in a ArrayList and sets it to the sectionsSpinner
     * @param list, ArrayList, list of values that will be set to the sectionsSpinner
     */
    void setSectionSpinnerValue(ArrayList<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(dataAdapter);
    }

    /**
     * Method to add a dog to the Database
     */
    void addDog(String group, String section, String breedName, String size, String coat, String energy){

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //filling contentValues
        contentValues.put(Contract.BreedsSchema.FIELD_GROUP,group);
        contentValues.put(Contract.BreedsSchema.FIELD_SECTION,section);
        contentValues.put(Contract.BreedsSchema.FIELD_NAME,breedName);
        contentValues.put(Contract.BreedsSchema.FIELD_SIZE,size);
        contentValues.put(Contract.BreedsSchema.FIELD_COAT,coat);
        contentValues.put(Contract.BreedsSchema.FIELD_ENERGY,energy);

        //inserting into the db
        db.insert(Contract.BreedsSchema.TABLE_NAME, null, contentValues);

        //setting a Toast mesasge that a dog has been added
        Toast.makeText(this, "Dog has been sucessfully added", Toast.LENGTH_SHORT).show();
        //reseting all the values after dog has been added
        sizeRG.clearCheck();
        coatRG.clearCheck();
        energyRG.clearCheck();
        breedNameET.setText("");
        groupSpinner.setSelection(0);
        sectionSpinner.setSelection(0);

    }

}
