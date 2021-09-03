package com.example.room_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.room_db.databinding.ActivityAddNewBinding;

public class AddNew extends AppCompatActivity {
    ActivityAddNewBinding b;
    public static final String reply = "app";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityAddNewBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(b.editWord.toString())) {
                    Intent in = getIntent();
                    Toast.makeText(AddNew.this, "Text cannot be Empty", Toast.LENGTH_SHORT).show();
                }else{
                    Intent in = getIntent();
                    in.putExtra(reply, b.editWord.getText().toString());
                    setResult(RESULT_OK,in);
                }
                finish();
            }
        });

    }
}