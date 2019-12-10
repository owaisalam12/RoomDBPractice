package com.owais.roomdbpractice;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private int id;

    @ColumnInfo(name = "task_name")
    private String name;

    @ColumnInfo(name = "task_description")
    private String description;

    @ColumnInfo(name = "task_finishBy")
    private String finishBy;

    @ColumnInfo(name = "task_finished")
    private boolean finished;

    @Ignore
    public Task() {
    }

    public Task(int id, String name, String description, String finishBy, boolean finished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.finishBy = finishBy;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinishBy() {
        return finishBy;
    }

    public void setFinishBy(String finishBy) {
        this.finishBy = finishBy;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
