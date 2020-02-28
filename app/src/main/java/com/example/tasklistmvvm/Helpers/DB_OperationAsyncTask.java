package com.example.tasklistmvvm.Helpers;

import android.os.AsyncTask;

import com.example.tasklistmvvm.Model.Note;
import com.example.tasklistmvvm.Room.DAO.NoteDao;

public class DB_OperationAsyncTask extends AsyncTask<Note,Void,Void> {
    private NoteDao noteDao;
    private int  dbOperationType;

    public DB_OperationAsyncTask(NoteDao noteDao,int dbOperaionType) {
        this.noteDao = noteDao;
        this.dbOperationType= dbOperaionType;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        switch (dbOperationType){
            case Utils.INSERT_OPERATION:
                noteDao.insert(notes[0]);
                break;
            case Utils.DELETE_OPERATION:
                noteDao.delete(notes[0]);
                break;
            case Utils.UPDATE_OPERATION:
                noteDao.update(notes[0]);
                break;
            case Utils.DELETE_ALL_OPERATION:
                noteDao.deleteAllNotes();
                break;
            default:break;
        }
        return null;
    }
}
