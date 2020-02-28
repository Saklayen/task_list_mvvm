package com.example.tasklistmvvm.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tasklistmvvm.Helpers.DB_OperationAsyncTask;
import com.example.tasklistmvvm.Helpers.Utils;
import com.example.tasklistmvvm.Model.Note;
import com.example.tasklistmvvm.Room.DAO.NoteDao;
import com.example.tasklistmvvm.Room.Database.NoteDataBase;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new DB_OperationAsyncTask(noteDao, Utils.INSERT_OPERATION).execute(note);
    }
    public void update(Note note){
        new DB_OperationAsyncTask(noteDao, Utils.UPDATE_OPERATION).execute(note);
    }
    public void delete(Note note){
        new DB_OperationAsyncTask(noteDao, Utils.DELETE_OPERATION).execute(note);
    }
    public void deleteAllNotes(){
        new DB_OperationAsyncTask(noteDao, Utils.DELETE_ALL_OPERATION).execute();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

}
