package com.example.room_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(App app);
    @Query("DELETE FROM app_table")
    void deleteAll();
    @Query("DELETE FROM app_table WHERE id =  :id")
    void deletethis(int id);
    @Query("SELECT*FROM app_table")
    LiveData<List<App>> getApps();
    @Query("UPDATE app_table SET name = :name WHERE name = :name")
    void update(String name);
}
