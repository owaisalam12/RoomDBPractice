package com.owais.roomdbpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddtaskActivity extends AppCompatActivity {

    private EditText editTextTaskName,getEditTextTaskDesc,getEditTextTaskFinishedBy;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        button=findViewById(R.id.task_submit);
        editTextTaskName=findViewById(R.id.task_name);
        getEditTextTaskDesc=findViewById(R.id.task_description);
        getEditTextTaskFinishedBy=findViewById(R.id.task_finishedBy);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editTextTaskName.getText().toString();
                String desc=getEditTextTaskDesc.getText().toString();
                String finishby=getEditTextTaskFinishedBy.getText().toString();
                if(TextUtils.isEmpty(name)) {
                    Toast.makeText(AddtaskActivity.this, "plz enter task", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(desc)) {
                    Toast.makeText(AddtaskActivity.this, "plz enter desc", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(finishby)) {
                    Toast.makeText(AddtaskActivity.this, "plz enter finishby ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("name",name);
                returnIntent.putExtra("desc",desc);
                returnIntent.putExtra("finishby",finishby);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
