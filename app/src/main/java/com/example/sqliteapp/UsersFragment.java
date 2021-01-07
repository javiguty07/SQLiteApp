package com.example.sqliteapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteapp.entities.User;
import com.example.sqliteapp.utilities.Utilities;

import java.util.ArrayList;


public class UsersFragment extends Fragment {

    ArrayList<User> usersList;
    RecyclerView recyclerViewUsers;

    ConectSQliteHelper conn;

    public UsersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_users, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerViewUsers = (RecyclerView) view;
            recyclerViewUsers.setLayoutManager(new LinearLayoutManager(context));
        }

        conn = new ConectSQliteHelper(getContext(), "db_users", null, 1);
        usersList = new ArrayList<>();

        insertUsersToUsersList();

        MyUsersRecyclerViewAdapter adapter = new MyUsersRecyclerViewAdapter(usersList);

        adapter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // String id = usersList.get(recyclerViewUsers.getChildAdapterPosition(view)).getId());
                Intent intent = new Intent(getContext(), SearchUserActivity.class);
                intent.putExtra("id", usersList.get(recyclerViewUsers.getChildAdapterPosition(view)).getId());
                intent.putExtra("name", usersList.get(recyclerViewUsers.getChildAdapterPosition(view)).getName());
                intent.putExtra("phone", usersList.get(recyclerViewUsers.getChildAdapterPosition(view)).getPhone());
                // Toast.makeText(getContext(),usersList.get(recyclerViewUsers.getChildAdapterPosition(view)).getId(),Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        recyclerViewUsers.setAdapter(adapter);

        return view;
    }

    private void insertUsersToUsersList() {
        SQLiteDatabase db = conn.getReadableDatabase();
        User user = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilities.TABLE_USERS, null);

        while (cursor.moveToNext()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setPhone(cursor.getString(2));

            usersList.add(user);
        }
    }
}