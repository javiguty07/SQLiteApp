package com.example.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRegister, btnSearchActivity , btnListUsersActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConectSQliteHelper conn = new ConectSQliteHelper(this,"db_users",null,1);

        findViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnSearchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchUserActivity.class);
                startActivity(intent);
            }
        });
        btnListUsersActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void findViews() {

        btnRegister = findViewById(R.id.btnRegister);
        btnSearchActivity = findViewById(R.id.btnSearchActivity);
        btnListUsersActivity = findViewById(R.id.buttonListUsersActivity);
    }
}