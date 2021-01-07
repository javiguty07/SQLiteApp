package com.example.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteapp.utilities.Utilities;
import com.google.android.material.textfield.TextInputLayout;

public class SearchUserActivity extends AppCompatActivity {

    TextInputLayout etId, etName, etPhone;
    Button btnSearch, btnUpdate, btnDelete;

    ConectSQliteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        conn = new ConectSQliteHelper(this, "db_users", null, 1);

        findViews();

        onClickSearchUser();
        onClickUpdateUser();
        onClickDeleteUser();
    }

    private void onClickDeleteUser() {
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                deleteUser();
            }
        });
    }

    private void onClickUpdateUser() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void onClickSearchUser() {
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //search();
                searchBySql();
            }
        });
    }

    private void deleteUser() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {etId.getEditText().getText().toString()};

        db.delete(Utilities.TABLE_USERS, Utilities.FIELD_ID + "=?", params);
        Toast.makeText(this, "Se elminino el usuario correctamente", Toast.LENGTH_LONG).show();
        db.close();
    }

    private void updateUser() {

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {etId.getEditText().getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilities.FIELD_NAME, etName.getEditText().getText().toString());
        values.put(Utilities.FIELD_PHONE, etPhone.getEditText().getText().toString());

        db.update(Utilities.TABLE_USERS, values, Utilities.FIELD_ID + "=?", params);

        Toast.makeText(this, "Actualizado correctamente", Toast.LENGTH_LONG).show();
        db.close();

    }

    private void searchBySql() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {etId.getEditText().getText().toString()};


        try {
            //Select name,phone from users where id = ?
            Cursor cursor = db.rawQuery("SELECT " + Utilities.FIELD_NAME + "," + Utilities.FIELD_PHONE + " FROM " + Utilities.TABLE_USERS + " WHERE " + Utilities.FIELD_ID + " =?", params);

            cursor.moveToFirst();

            etName.getEditText().setText(cursor.getString(0));
            etPhone.getEditText().setText(cursor.getString(1));
            cursor.close();

        } catch (Exception e) {

            Toast.makeText(this, "El documento no existe", Toast.LENGTH_LONG).show();
            clean();
        }
    }

    private void findViews() {

        etId = findViewById(R.id.textSearchId);
        etName = findViewById(R.id.textSearchName);
        etPhone = findViewById(R.id.textSearchPhone);
        btnSearch = findViewById(R.id.buttonSearch);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
    }

    private void search() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {etId.getEditText().getText().toString()};
        String[] fields = {Utilities.FIELD_NAME, Utilities.FIELD_PHONE};

        try {
            Cursor cursor = db.query(Utilities.TABLE_USERS, fields, Utilities.FIELD_ID + "=?", params, null, null, null);
            cursor.moveToFirst();
            etName.getEditText().setText(cursor.getString(0));
            etPhone.getEditText().setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "El documento no existe", Toast.LENGTH_LONG).show();
            clean();
        }

    }

    private void clean() {
        etName.getEditText().setText("");
        etPhone.getEditText().setText("");
    }


}