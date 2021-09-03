package com.example.room_db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository viewRepo;
    private LiveData<List<App>> viewList;
    public AppViewModel(@NonNull Application application) {
        super(application);
        viewRepo = new AppRepository(application);
        viewList = viewRepo.getRepoList();

    }
    LiveData<List<App>> getViewList(){
        return viewList;
    }
    void viewInsert(App app){
        viewRepo.repoInsert(app);
    }
    void viewDelete(int id){viewRepo.repoDelete(id);}
}
