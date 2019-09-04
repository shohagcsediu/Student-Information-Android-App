package com.shohagapp.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shohagapp.student.database.StudentAdapter;
import com.shohagapp.student.database.StudentContract;
import com.shohagapp.student.database.StudentDBHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private StudentAdapter mAdapter;

    private EditText mnameET, mrollET, mdeptET;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentDBHelper dbHelper = new StudentDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StudentAdapter(this,getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        mnameET = findViewById(R.id.snameET);
        mrollET = findViewById(R.id.srollET);
        mdeptET = findViewById(R.id.sdeptET);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudentInfo();
            }
        });
    }

    private void saveStudentInfo() {

        if(mnameET.getText().toString().trim().length() == 0 || mrollET.getText().toString().trim().length() == 0){
            return;
        }
            String name = mnameET.getText().toString();
            String roll = mrollET.getText().toString();
            String dept = mdeptET.getText().toString();

            ContentValues cv = new ContentValues();

            cv.put(StudentContract.StudentEntry.COL_NAME, name);
            cv.put(StudentContract.StudentEntry.COL_ROLL, roll);
            cv.put(StudentContract.StudentEntry.COL_DEPT, dept);

            mDatabase.insert(StudentContract.StudentEntry.TABLE_NAME,null,cv);
            mAdapter.swapCursor(getAllItems());

            mnameET.getText().clear();

    }

    private void removeItem(long id) {
        mDatabase.delete(StudentContract.StudentEntry.TABLE_NAME,
                StudentContract.StudentEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                StudentContract.StudentEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                StudentContract.StudentEntry.COL_NAME + " DESC"
        );
    }
}