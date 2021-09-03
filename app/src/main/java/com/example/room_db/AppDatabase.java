package com.example.room_db;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {App.class},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao dbDao();
    public static volatile AppDatabase instance;
    private static final int no_of_threads = 4;
    static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(no_of_threads);

    static AppDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (AppDatabase.class) {
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"app_database" )
                            .addCallback(databaseCallback).build();
                }
            }
        }
        return instance;

    }
    private static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    AppDao callbackDao = instance.dbDao();
                    App app = new App("hello world");
                    callbackDao.insert(app);
                }
            });
        }
    } ;

}
