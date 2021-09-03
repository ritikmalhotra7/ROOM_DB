package com.example.room_db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {
    private AppDao repoDao;
    private LiveData<List<App>> repoList;

    public AppRepository(Application application) {
        repoDao = AppDatabase.getDatabase(application).dbDao();
        repoList = repoDao.getApps();
    }
    LiveData<List<App>> getRepoList(){
        return repoList;
    }
    void repoInsert(App app){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repoDao.insert(app);
            }
        });
    }
    void repoDelete(int id){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repoDao.deletethis(id);
            }
        });
    }
}
