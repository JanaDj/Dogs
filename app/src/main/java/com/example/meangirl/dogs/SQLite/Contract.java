package com.example.meangirl.dogs.SQLite;

import android.provider.BaseColumns;

public class Contract {
    //every table would represent an inner class
    public static final  class BreedsSchema implements BaseColumns {
        //table name
        public static final String TABLE_NAME = "breeds_table";
        //columns
        public static final String _ID = BaseColumns._ID;
        public static final String FIELD_GROUP = "breed_group";
        public static final String FIELD_SECTION = "section";
        public static final String FIELD_NAME= "name";
        public static final String FIELD_SIZE = "size";
        public static final String FIELD_COAT = "coat";
        public static final String FIELD_ENERGY = "energy";
    }

}
