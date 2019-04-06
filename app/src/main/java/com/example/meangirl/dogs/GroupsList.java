package com.example.meangirl.dogs;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.meangirl.dogs.Activities.BreedsList;
import com.example.meangirl.dogs.Activities.DogPicker;
import com.example.meangirl.dogs.SQLite.Contract;
import com.example.meangirl.dogs.SQLite.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupsList extends AppCompatActivity {
 private ExpandableListView listView;
 private ExpandableListAdapter listAdapter;
 private List<String> listDataHandler;
 private HashMap <String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_list);
        listView = findViewById(R.id.groupsList);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHandler, listHashMap);
        listView.setAdapter(listAdapter);
        /**
         * On click listener for the sections lists
         * Users are redirected to the BreedsList view and name of the section is sent over by the intent putextra
         */
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                TextView tv= (TextView) view.findViewById(R.id.listItem);
                String section= tv.getText().toString();
                String searchQuery = "SELECT " + Contract.BreedsSchema.FIELD_NAME + " FROM " + Contract.BreedsSchema.TABLE_NAME +
                        " WHERE " + Contract.BreedsSchema.FIELD_SECTION + " LIKE '%" +section + "%'";
                DatabaseHelper dbHelper = new DatabaseHelper(GroupsList.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor c = db.rawQuery(searchQuery,null);
                ArrayList<String> dogsList = new ArrayList<>();
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
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(GroupsList.this);
                    builder1.setMessage("Woops! It Looks like no dogs were found here. Please try another section or add a dog from the Home page.");
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
                c.close();
                db.close();
                return true;
            }
        });
        }
    /**
     * Method initData fills the data for the ArrayList and HashMap
     */
    private void initData() {
        FCIData fciData = FCIData.getInstance();
        listDataHandler = new ArrayList<>();
        listHashMap = new HashMap<>();
        listDataHandler = fciData.getGroupsData();
        //adding lists of sections to the hashMap
        listHashMap.put(fciData.getGroupsData().get(0), fciData.getGroup1());
        listHashMap.put(fciData.getGroupsData().get(1), fciData.getGroup2());
        listHashMap.put(fciData.getGroupsData().get(2), fciData.getGroup3());
        listHashMap.put(fciData.getGroupsData().get(3), fciData.getGroup4());
        listHashMap.put(fciData.getGroupsData().get(4), fciData.getGroup5());
        listHashMap.put(fciData.getGroupsData().get(5), fciData.getGroup6());
        listHashMap.put(fciData.getGroupsData().get(6), fciData.getGroup7());
        listHashMap.put(fciData.getGroupsData().get(7), fciData.getGroup8());
        listHashMap.put(fciData.getGroupsData().get(8), fciData.getGroup9());
        listHashMap.put(fciData.getGroupsData().get(9), fciData.getGroup10());
    }
}
