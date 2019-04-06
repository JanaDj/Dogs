package com.example.meangirl.dogs.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.meangirl.dogs.SQLite.Contract;
import com.example.meangirl.dogs.SQLite.DatabaseHelper;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BreedsList extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get data from intent
        Intent intent = getIntent();
        ArrayList<String> dogsList = intent.getStringArrayListExtra("dogsList");

        //assign data to the breedsList
        if(dogsList != null) {
            ListAdapter breedsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogsList);
            setListAdapter(breedsAdapter);
        }
        /**
         * On click listener for the breeds in the list
         * User gets redirected to the BreedView and breed name is sent over
         */
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String breedName = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getBaseContext(), BreedInfo.class);
                intent.putExtra("breedName", breedName);
                startActivity(intent);

            }
        });
    }
}
