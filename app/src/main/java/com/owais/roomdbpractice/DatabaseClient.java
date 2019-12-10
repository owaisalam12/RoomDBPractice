package com.owais.roomdbpractice;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient INSTANCE;

    private TaskAppDatabase taskAppDatabase;

    public DatabaseClient(Context context) {
        this.context = context;

        taskAppDatabase= Room.databaseBuilder(context,TaskAppDatabase.class,"MyToDos").build();


    }
    public static synchronized DatabaseClient getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE=new DatabaseClient(context);
        }

        return INSTANCE;
    }

    public TaskAppDatabase taskAppDatabase(){
        return taskAppDatabase;
    }

    private RoomDatabase.Callback callback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
