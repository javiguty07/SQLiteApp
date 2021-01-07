package com.example.sqliteapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteapp.utilities.Utilities;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout etId, etName, etPhone;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //registerUsers();
                registerUsersBySql();
            }
        });

    }

    private void registerUsersBySql() {
        ConectSQliteHelper conn = new ConectSQliteHelper(this, "db_users", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        String id = etId.getEditText().getText().toString();
        String name = etName.getEditText().getText().toString();
        String phone = etPhone.getEditText().getText().toString();


        String insert = "INSERT INTO " + Utilities.TABLE_USERS + "(" + Utilities.FIELD_ID + ","
                + Utilities.FIELD_NAME + "," + Utilities.FIELD_PHONE + ") " +
                "values(" + id + "," + "'" + name + "'" + "," + "'" + phone + "')";

        db.execSQL(insert);

        Toast.makeText(getApplicationContext(), "Id de registro: " + id, Toast.LENGTH_LONG).show();

        db.close();
    }

    private void findViews() {

        etId = findViewById(R.id.textId);
        etName = findViewById(R.id.textName);
        etPhone = findViewById(R.id.textPhone);
        btnSignUp = findViewById(R.id.btnSignUp);
    }


    private void registerUsers() {

        ConectSQliteHelper conn = new ConectSQliteHelper(this, "db_users", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.FIELD_ID, etId.getEditText().getText().toString());
        values.put(Utilities.FIELD_NAME, etName.getEditText().getText().toString());
        values.put(Utilities.FIELD_PHONE, etPhone.getEditText().getText().toString());

        Long idResult = db.insert(Utilities.TABLE_USERS, Utilities.FIELD_ID, values);

        Toast.makeText(getApplicationContext(), "Id de registro: " + idResult, Toast.LENGTH_LONG).show();
        db.close();

    }
}