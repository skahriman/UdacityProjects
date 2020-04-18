package com.exmaple.android.roomdatabaseexample2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public abstract MyDao getDao();
}
