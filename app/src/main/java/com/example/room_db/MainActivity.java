package com.example.room_db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.room_db.databinding.ActivityMainBinding;
import com.example.room_db.databinding.RecycylerViewItemsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickListeners {
    private RecyclerViewAdapter adapter;
    private AppViewModel appViewModel;
    ActivityMainBinding b;
    private RecyclerView rv;


    public static final int requestCode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        appViewModel.getViewList().observe(this, new Observer<List<App>>() {
            @Override
            public void onChanged(List<App> apps) {
                adapter.submitList(apps);
            }
        });
        adapter = new RecyclerViewAdapter(new RecyclerViewAdapter.AppDiff(),this);
        rv = findViewById(R.id.recyclerview);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        b.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,AddNew.class);
                startActivityForResult(in,requestCode);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            App app = new App(data.getStringExtra(AddNew.reply));
            appViewModel.viewInsert(app);
        }else{
            Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void clicked(int id) {
        appViewModel.viewDelete(id);
        //appViewModel.viewUpdate(id);
    }
}