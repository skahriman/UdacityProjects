package com.exmaple.android.roomdatabaseexample2;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDao {
    @Insert
    void add(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("select * from users")
    List<User> getUsers();

}
