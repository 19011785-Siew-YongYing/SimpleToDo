package sg.edu.rp.c346.id19011785.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText addDelTask;
    Button addBtn, delBtn, clearBtn;

    Spinner spnAddDel;

    ListView tasksList;
    ArrayList<String> tdTasksL;
    ArrayAdapter<String> tdTasksA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDelTask = findViewById(R.id.addDelTasks);
        addBtn = findViewById(R.id.addBtnTask);
        delBtn = findViewById(R.id.delBtnTask);
        clearBtn = findViewById(R.id.clearBtnTask);

        spnAddDel = findViewById(R.id.spinner); // Enhancement 1 Part 1

        tasksList = findViewById(R.id.todoList);
        tdTasksL = new ArrayList<String>();

        tdTasksA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tdTasksL);
        tasksList.setAdapter(tdTasksA);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nTask = addDelTask.getText().toString();
                if (nTask.length() > 0){
                    tdTasksL.add(nTask);
                    tdTasksA.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "New Task Added", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Input Task To Do", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskDel = addDelTask.getText().toString();
                int pos = Integer.parseInt(taskDel);
                if (tdTasksL.size() > 0) {
                    if (pos < tdTasksL.size()) {
                        tdTasksL.remove(pos);
                        tdTasksA.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Task Deleted!", Toast.LENGTH_LONG).show();
                    }
                    else { // Enhancement 2 Part 2
                        Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_LONG).show();
                    }
                }
                else if (tdTasksL.size() <= 0){ // Enhancement 2 Part 1
                    Toast.makeText(MainActivity.this, "You don't have any task to remove :/", Toast.LENGTH_LONG).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tdTasksL.clear();
                tdTasksA.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Tasks cleared!", Toast.LENGTH_LONG).show();
                String input = addDelTask.getText().toString();
                if (input.length() > 0){ // Remove text entered by user as well
                    addDelTask.setText("");
                }
            }
        });

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Enhancement 1 Part 1
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: // when "Add a new task" is selected
                        addDelTask.setHint(R.string.enter);
                        addBtn.setClickable(true); // Enhancement 1
                        delBtn.setClickable(false); // Enhancement 1
                        break;
                    case 1: // when "Remove a task" is selected
                        addDelTask.setHint(R.string.index);
                        addBtn.setClickable(false); // Enhancement 1
                        delBtn.setClickable(true); // Enhancement 1
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}