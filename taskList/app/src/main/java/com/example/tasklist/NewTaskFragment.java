 package com.example.tasklist;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasklist.ui.main.SectionsPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


 /**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewTaskFragment extends Fragment {

    public NewTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewTask.
     */
    public static NewTaskFragment newInstance() {
        NewTaskFragment fragment = new NewTaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_new_task, container, false);
        final EditText taskNameField = view.findViewById(R.id.task_name_field);
        final EditText deadlineField = view.findViewById(R.id.deadline_field);
        String date = SectionsPagerAdapter.formatDate(new Date());
        deadlineField.setText(date);
        Button btn = view.findViewById(R.id.new_task_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
                // Get test from fields
                String taskName = taskNameField.getText().toString();
                Date deadline = (deadlineField.getText().toString() == "") ? new Date() : new SimpleDateFormat("dd/MM/yyyy").parse(deadlineField.getText().toString());

                // Add item to the tasks list if not empty
                if (!taskName.equals("")) {
                    SectionsPagerAdapter.addItem(taskName, deadline);
                    Toast.makeText(getContext(), "Task added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Task name required", Toast.LENGTH_SHORT).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            }
        });
        return view;
    }
}
