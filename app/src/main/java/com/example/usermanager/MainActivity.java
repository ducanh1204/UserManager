package com.example.usermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySqliteOpenHelper mySqliteOpenHelper;
    private UserDAO userDAO;
    private List<User> userList;
    private RecyclerView rvList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = findViewById(R.id.rvList);
        btnAddData = findViewById(R.id.btnAddData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);


        userList = new ArrayList<>();

        mySqliteOpenHelper = new MySqliteOpenHelper(this);
        mySqliteOpenHelper.createDataBase();

        userDAO = new UserDAO(this);
        userList = userDAO.getAll();
        if (userList.size() > 0) {
            recyclerViewAdapter = new RecyclerViewAdapter(userList, this, new RecyclerViewAdapter.OnClickItemListener() {
                @Override
                public void onClick_Delte(int position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Bạn có chắc chắn muốn xóa Sách này?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDAO.deleteUser(userList.get(position).getId());
                            userList.remove(position);
                            recyclerViewAdapter.notifyItemRemoved(position);
                        }
                    });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                @Override
                public void onClick_Edit(int position) {
                    Intent intent = new Intent(MainActivity.this,UpdateDataActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position",position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            rvList.setAdapter(recyclerViewAdapter);
        }

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InsertDataActivity.class);
                startActivity(intent);
            }
        });
    }


}