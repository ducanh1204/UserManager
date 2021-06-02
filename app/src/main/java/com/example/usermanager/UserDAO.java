package com.example.usermanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private MySqliteOpenHelper mySqliteOpenHelper;

    public UserDAO(Context context) {
        this.mySqliteOpenHelper = new MySqliteOpenHelper(context);
    }

    private String TABLE_NAME = "User";
    private String ID = "ID";
    private String USER_NAME = "UserName";
    private String PASSWORD = "Password";
    private String FULL_NAME = "FullName";
    private String GENDER = "Gender";
    private String BIRTHDAY = "Birthday";
    private String ADDRESS = "Address";
    private String PHOTO = "Photo";
    private String FINGER = "Finger";
    private String CCCD = "CCCD";


    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = mySqliteOpenHelper.getReadableDatabase();

        String SQL = "SELECT * FROM " + TABLE_NAME ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    User user = new User();

                    user.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                    user.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                    user.setFullName(cursor.getString(cursor.getColumnIndex(FULL_NAME)));
                    user.setGender(cursor.getString(cursor.getColumnIndex(GENDER)));
                    user.setBirthday(cursor.getString(cursor.getColumnIndex(BIRTHDAY)));
                    user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                    user.setPhoto(cursor.getBlob(cursor.getColumnIndex(PHOTO)));
                    user.setFinger(cursor.getBlob(cursor.getColumnIndex(FINGER)));
                    user.setCccd(cursor.getString(cursor.getColumnIndex(CCCD)));

                    userList.add(user);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }
        return userList;
    }

    public long insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = mySqliteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, user.getId());
        contentValues.put(USER_NAME, user.getUserName());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(FULL_NAME, user.getFullName());
        contentValues.put(GENDER, user.getGender());
        contentValues.put(BIRTHDAY, user.getBirthday());
        contentValues.put(ADDRESS, user.getAddress());
        contentValues.put(PHOTO, user.getPhoto());
        contentValues.put(FINGER, user.getFinger());
        contentValues.put(CCCD, user.getCccd());

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }
    public long updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = mySqliteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, user.getId());
        contentValues.put(USER_NAME, user.getUserName());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(FULL_NAME, user.getFullName());
        contentValues.put(GENDER, user.getGender());
        contentValues.put(BIRTHDAY, user.getBirthday());
        contentValues.put(ADDRESS, user.getAddress());
        contentValues.put(PHOTO, user.getPhoto());
        contentValues.put(FINGER, user.getFinger());
        contentValues.put(CCCD, user.getCccd());

        long result = sqLiteDatabase.update(TABLE_NAME, contentValues,ID + "=?",new String[user.getId()]);
        sqLiteDatabase.close();
        return result;
    }
    public void deleteUser(String id) {
        SQLiteDatabase sqLiteDatabase = mySqliteOpenHelper.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{id});

    }
}
