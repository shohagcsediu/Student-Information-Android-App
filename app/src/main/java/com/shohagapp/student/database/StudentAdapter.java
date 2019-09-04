package com.shohagapp.student.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shohagapp.student.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.viewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public StudentAdapter (Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.student_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(StudentContract.StudentEntry.COL_NAME));
        String roll = mCursor.getString(mCursor.getColumnIndex(StudentContract.StudentEntry.COL_ROLL));
        String dept = mContext.getString(mCursor.getColumnIndex(StudentContract.StudentEntry.COL_DEPT));

        holder.nameText.setText(name);
        holder.rollText.setText(roll);
        holder.deptText.setText(dept);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView rollText;
        public TextView deptText;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textViewname);
            rollText = itemView.findViewById(R.id.textViewroll);
            deptText = itemView.findViewById(R.id.textViewdept);
        }
    }


}
