package com.example.sqliteapp.utilities;

public class Utilities {

    //Constantes campos tabla users
    public static String TABLE_USERS = "users";
    public static String FIELD_ID = "id";
    public static String FIELD_NAME = "name";
    public static String FIELD_PHONE = "phone";

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USERS
            + "(" + FIELD_ID + " INTEGER, " + FIELD_NAME + " TEXT," + FIELD_PHONE + " TEXT)";
}
