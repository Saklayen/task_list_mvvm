package com.example.tasklistmvvm.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tasklistmvvm.Adapters.NoteAdapter;
import com.example.tasklistmvvm.Helpers.OnItemClickListener;
import com.example.tasklistmvvm.Helpers.Utils;
import com.example.tasklistmvvm.Model.Note;
import com.example.tasklistmvvm.R;
import com.example.tasklistmvvm.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saklayen.gscarrier.GSCarrier;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    FloatingActionButton addBtn;
    Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //statusbar color according to scren
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/

        nextBtn = findViewById(R.id.next_screen_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,TopScorer.class));

            }
        });

        addBtn = findViewById(R.id.add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivityForResult(intent, Utils.SAVE_NOTE_REQUEST_CODE);

            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // update recycler view
                //Toast.makeText(MainActivity.this,"On Changed",Toast.LENGTH_LONG).show();
                noteAdapter.setNotes(notes);
            }
        });

        //swipe delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        noteAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                intent.putExtra(Utils.ID_RESULT, String.valueOf(note.getId()));
                intent.putExtra(Utils.TITLE_RESULT, note.getTitle());
                intent.putExtra(Utils.PRIORITY_RESULT, String.valueOf(note.getPriority()));

                startActivityForResult(intent, Utils.EDIT_NOTE_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utils.SAVE_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(Utils.TITLE_RESULT);
            int priority = Integer.valueOf(data.getStringExtra(Utils.PRIORITY_RESULT));

            Log.e("rcv result...", "onActivityResult: " + title + " " + priority);

            Note note = new Note(title, "", priority);

            Log.e("rcv result...", "onActivityResult: " + note.getTitle() + " " + note.getPriority());
            noteViewModel.insert(note);

        } else if (requestCode == Utils.EDIT_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = Integer.valueOf(data.getStringExtra(Utils.ID_RESULT));
            String title = data.getStringExtra(Utils.TITLE_RESULT);
            int priority = Integer.valueOf(data.getStringExtra(Utils.PRIORITY_RESULT));

            Log.e("rcv result...", "onActivityResult: " + title + " " + priority);

            Note note = new Note(title, "", priority);
            note.setId(id);

            Log.e("rcv result...", "onActivityResult: " + note.getTitle() + " " + note.getPriority());
            noteViewModel.update(note);

        }
    }
}
