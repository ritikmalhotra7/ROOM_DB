package com.example.room_db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "app_table")
public class App {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public App(@NonNull String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    @NonNull
    public String getName() {
        return name;
    }
}
