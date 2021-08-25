package com.example.secondapp;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.secondapp.database.UserDbSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDbSchema.Cols.UUID));
        String userName = getString(getColumnIndex(UserDbSchema.Cols.NAME));
        String userLastName = getString(getColumnIndex(UserDbSchema.Cols.LASTNAME));
        String phone = getString(getColumnIndex(UserDbSchema.Cols.PHONE));
        User user = new User(UUID.fromString(uuidString));
        user.setName(userName);
        user.setLastName(userLastName);
        user.setPhone(phone);
        return user;
    }
}
