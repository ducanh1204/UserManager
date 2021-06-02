package com.example.usermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
            recyclerViewAdapter = new RecyclerViewAdapter(userList, this);
            rvList.setAdapter(recyclerViewAdapter);
        }

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check username, và check id
                String username = "Ducanh";
                User user = new User();
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getUserName().equals(username)) {
                        Toast.makeText(MainActivity.this, "Tên người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                int id = 0;
                for (int i = 0; i < userList.size(); i++) {
                    if (id < userList.get(i).getId())
                        id = userList.get(i).getId();
                }
                id++;

                user.setId(id);
                user.setUserName(username);
                user.setPassword("Ducanh12");
                user.setFullName("NGUYỄN ĐỨC ANH");
                user.setGender("NAM");
                user.setBirthday("12/04/2000");
                user.setAddress("Ha Noi");
                user.setCccd("030200004945");
                user.setPhoto(null);
                user.setFinger(null);

                long result = userDAO.insertUser(user);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    recreate();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}