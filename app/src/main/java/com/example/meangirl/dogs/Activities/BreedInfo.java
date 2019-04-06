package com.example.meangirl.dogs.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meangirl.dogs.R;
import com.example.meangirl.dogs.SQLite.Contract;
import com.example.meangirl.dogs.SQLite.DatabaseHelper;

public class BreedInfo extends AppCompatActivity {

    TextView nameTV, groupTV, sectionTV, sizeTV, coatTV, energyTV;
    Button homeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breed_view);
        connectWithXML();

        Intent intent = getIntent();
        String breedName = intent.getStringExtra("breedName");

        String searchQuery = "SELECT *"  + " FROM " + Contract.BreedsSchema.TABLE_NAME +
                " WHERE " + Contract.BreedsSchema.FIELD_NAME + " LIKE '" + breedName + "'";
        DatabaseHelper dbHelper = new DatabaseHelper(BreedInfo.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(searchQuery,null);
        if(c.moveToFirst()) {
            nameTV.setText(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_NAME)));
            groupTV.setText(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_GROUP)));
            sectionTV.setText(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_SECTION)));
            sizeTV.setText(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_SIZE)));
            coatTV.setText(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_COAT)));
            energyTV.setText(c.getString(c.getColumnIndex(Contract.BreedsSchema.FIELD_ENERGY)));
        }
        c.close();
        db.close();


        /**
         * Onclick listener for the homeBtn
         * Redirects user to the home page
         */
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);

            }
        });
    }

    /**
     * Method to connect XML components with code
     */
    void connectWithXML() {
        //text views
        nameTV = findViewById(R.id.nameTV);
        groupTV = findViewById(R.id.groupTV);
        sectionTV = findViewById(R.id.sectionTV);
        sizeTV = findViewById(R.id.sizeTv);
        coatTV = findViewById(R.id.coatTV);
        energyTV = findViewById(R.id.energyTV);
        //buttons
        homeBtn = findViewById(R.id.homeBtn);
    }
}
