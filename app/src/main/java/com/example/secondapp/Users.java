package com.example.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.secondapp.database.UserBaseHelper;
import com.example.secondapp.database.UserDbSchema;

import java.util.ArrayList;
import java.util.UUID;

public class Users {
    private final SQLiteDatabase database;

    public Users(Context context) {
        Context context1 = context.getApplicationContext();
        this.database = new UserBaseHelper(context1).getWritableDatabase();
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);
        database.close();
    }

    public void updateUser(User user){
        ContentValues values = getContentValues(user);
        String[] uuidArray = new String[] {user.getUuid().toString()};
        database.update(UserDbSchema.UserTable.NAME, values, "uuid = ?", uuidArray);
        database.close();
    }

    public void deleteUser(UUID uuid){
        String[] uuidArray = new String[] {uuid.toString()};
        database.delete(UserDbSchema.UserTable.NAME, "uuid = ?", uuidArray);
        database.close();
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.Cols.NAME, user.getName());
        values.put(UserDbSchema.Cols.LASTNAME, user.getLastName());
        values.put(UserDbSchema.Cols.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers(){
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME,null,null,null,null,null,null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return userList;
    }
}
