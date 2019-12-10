package com.owais.roomdbpractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskArrayList;
    private int NEW_DATA_CODE=11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskArrayList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        floatingActionButton=findViewById(R.id.fab);

        getAllTasks();
        taskAdapter=new TaskAdapter(taskArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(taskAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AddtaskActivity.class);
                startActivityForResult(intent,NEW_DATA_CODE);
            }
        });





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_DATA_CODE) {
            if(resultCode == Activity.RESULT_OK){
                String name=data.getStringExtra("name");
                String desc=data.getStringExtra("desc");
                String finishby=data.getStringExtra("finishby");
                createTask(new Task(0,name,desc,finishby,false));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    private void getAllTasks(){
        class getAllTasksAsyncTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                taskArrayList.addAll(DatabaseClient
                        .getInstance(getApplicationContext())
                        .taskAppDatabase()
                        .getTaskDao()
                        .getAllTask());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                taskAdapter.notifyDataSetChanged();
            }
        }
        new getAllTasksAsyncTask().execute();
    }

    private void createTask(final Task task){
        class createTastAsyncTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
               long id= DatabaseClient
                        .getInstance(getApplicationContext())
                        .taskAppDatabase()
                        .getTaskDao().createTask(task);
               Task task1=DatabaseClient
                       .getInstance(getApplicationContext())
                       .taskAppDatabase()
                       .getTaskDao().getTaskbyId(id);
               if(task1!=null){
                   taskArrayList.add(task1);
               }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                taskAdapter.notifyDataSetChanged();

            }
        }
    new createTastAsyncTask().execute();

    }

}
