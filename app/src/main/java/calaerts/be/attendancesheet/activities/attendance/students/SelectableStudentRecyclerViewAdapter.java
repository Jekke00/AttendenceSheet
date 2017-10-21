package calaerts.be.attendancesheet.activities.attendance.students;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.student.StudentInteractionListener;
import calaerts.be.attendancesheet.model.Student;

public class SelectableStudentRecyclerViewAdapter extends RecyclerView.Adapter<SelectableStudentRecyclerViewAdapter.ViewHolder> {

    private StudentInteractionListener mListener;
    private List<Student> students = new ArrayList<>();

    public SelectableStudentRecyclerViewAdapter(StudentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_student, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = students.get(position);
        holder.checkBox.setText(students.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onStudentSelected(holder.mItem);
                }
            }
        });
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CheckBox checkBox;
        public Student mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            checkBox = view.findViewById(R.id.id);
        }
    }
}
