package com.example.usermanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InsertDataActivity extends AppCompatActivity {

    private UserDAO userDAO;
    private EditText edtUserName,edtPassword,edtFullName,edtGender,edtBirthday,edtAddress,edtCCCD;
    private List<User> userList;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtFullName = findViewById(R.id.edtFullName);
        edtGender = findViewById(R.id.edtGender);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtAddress = findViewById(R.id.edtAddress);
        edtCCCD = findViewById(R.id.edtCCCD);
        btnSave = findViewById(R.id.btnSave);

        userList = new ArrayList<>();
        userDAO = new UserDAO(this);
        userList = userDAO.getAll();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    public void addData(){
        User user = new User();
        user.setUserName(null);
        if(!edtUserName.getText().toString().trim().equals("")){
            // check username k trùng
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserName().equals(edtUserName.getText().toString().trim())) {
                    Toast.makeText(InsertDataActivity.this, "Tên người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            user.setUserName(edtUserName.getText().toString().trim());
        }

        user.setPassword(null);
        if(!edtPassword.getText().toString().trim().equals("")){
            user.setPassword(edtPassword.getText().toString().trim());
        }
        user.setFullName(null);
        if(!edtFullName.getText().toString().trim().equals("")){
            user.setFullName(edtFullName.getText().toString().trim());
        }
        user.setGender(null);
        if(!edtGender.getText().toString().trim().equals("")){
            user.setGender(edtGender.getText().toString().trim());
        }
        user.setBirthday(null);
        if(!edtBirthday.getText().toString().trim().equals("")){
            user.setBirthday(edtBirthday.getText().toString().trim());
        }
        user.setAddress(null);
        if(!edtAddress.getText().toString().trim().equals("")){
            user.setAddress(edtAddress.getText().toString().trim());
        }
        user.setCccd(null);
        if(!edtCCCD.getText().toString().trim().equals("")){
            user.setCccd(edtCCCD.getText().toString().trim());
        }
        user.setPhoto(null);
        user.setFinger(null);

        if(user.getUserName()==null||user.getPassword()==null){
            Toast.makeText(InsertDataActivity.this, "Bắt buộc nhập username và password", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = userDAO.insertUser(user);
        if (result > 0) {
            Toast.makeText(InsertDataActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InsertDataActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(InsertDataActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}