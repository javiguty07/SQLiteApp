package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                search();
            }
        });
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