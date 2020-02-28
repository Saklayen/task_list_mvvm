package com.example.tasklistmvvm.Room.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tasklistmvvm.Model.Note;
import com.example.tasklistmvvm.Room.DAO.NoteDao;

@Database(entities =  {Note.class},version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDbAsyncTask(instance).execute();
        }


    };

    /*private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDataBase db){
            noteDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Brush", "1st note", 5));
            noteDao.insert(new Note("Code", "2st note", 8));
            noteDao.insert(new Note("Eat", "3rdt note", 10));
            return null;
        }
    }*/
}
