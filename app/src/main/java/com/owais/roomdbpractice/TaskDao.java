package com.owais.roomdbpractice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    long createTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("select * from tasks_table")
    List<Task> getAllTask();

    @Query("select * from tasks_table where task_id=:taskId")
    Task getTaskbyId(long taskId);

}
