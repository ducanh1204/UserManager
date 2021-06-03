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

public class UpdateDataActivity extends AppCompatActivity {

    private UserDAO userDAO;
    private EditText edtUserName,edtPassword,edtFullName,edtGender,edtBirthday,edtAddress,edtCCCD;
    private List<User> userList;
    private Button btnSave;
    private int position;
    private int id;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
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

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");
        id = userList.get(position).getId();
        username = userList.get(position).getUserName();
        edtUserName.setText(userList.get(position).getUserName());
        edtPassword.setText(userList.get(position).getPassword());
        edtFullName.setText(userList.get(position).getFullName());
        edtGender.setText(userList.get(position).getGender());
        edtBirthday.setText(userList.get(position).getBirthday());
        edtAddress.setText(userList.get(position).getAddress());
        edtCCCD.setText(userList.get(position).getCccd());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    public void updateData(){
        User user = new User();

        user.setId(id);

        user.setUserName(null);
        if(!edtUserName.getText().toString().trim().equals("")){

            // check username k trùng
            if(!edtUserName.getText().toString().trim().equals(username)){
                for (int i = 0; i < userList.size(); i++) {
                    if(i!=position){
                        if (userList.get(i).getUserName().equals(edtUserName.getText().toString().trim())) {
                            Toast.makeText(UpdateDataActivity.this, "Tên người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
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
            Toast.makeText(UpdateDataActivity.this, "Bắt buộc nhập username và password", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = userDAO.updateUser(user);
        if (result > 0) {
            Toast.makeText(UpdateDataActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateDataActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(UpdateDataActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}