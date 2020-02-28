package com.example.tasklistmvvm.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tasklistmvvm.Helpers.Utils;
import com.example.tasklistmvvm.Model.Note;
import com.example.tasklistmvvm.R;

import java.util.List;

public class AddNote extends AppCompatActivity {

    Button save,back;
    EditText title, priority;
    String idRcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        save = findViewById(R.id.save_btn);
        back = findViewById(R.id.back_btn);

        title = findViewById(R.id.title_input);
        priority = findViewById(R.id.priority_input);

        Intent intent = getIntent();

        if (intent.hasExtra(Utils.ID_RESULT)){
             title.setText(intent.getStringExtra(Utils.TITLE_RESULT));
             priority.setText(intent.getStringExtra(Utils.PRIORITY_RESULT));
             idRcv = intent.getStringExtra(Utils.ID_RESULT);

            Log.e("id rcv", "onCreate: "+idRcv );
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveNote(){
        String ttl, prio;
        ttl = title.getText().toString().trim();
        prio = priority.getText().toString().trim();

        if (ttl.isEmpty()){
            title.setError("Title is mandatory");
            return;
        }
        if (prio.isEmpty()){
            priority.setError("Priority is mandatory");
            return;
        }

        Intent data = new Intent();
        data.putExtra(Utils.TITLE_RESULT,ttl);
        data.putExtra(Utils.PRIORITY_RESULT,prio);

        if (idRcv != null){
            data.putExtra(Utils.ID_RESULT,idRcv);
        }

        setResult(RESULT_OK,data);
        finish();
    }
}
