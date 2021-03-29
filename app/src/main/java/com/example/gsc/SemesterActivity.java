package com.example.gsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SemesterActivity extends AppCompatActivity {
    String branchName = "Not selected",courseName = "Not selected",universityName = "Not selected";
    String[] semNumber = {"1", "2", "3", "4", "5","6","7","8"};
    private myAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        TextView head=findViewById(R.id.main_header);
        head.setText(R.string.select_semester);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        setOnClickListener();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new myAdapter(semNumber, listener));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universityName=extras.getString("universityName");
            courseName = extras.getString("courseName");
            branchName = extras.getString("branchName");
        }
//        Toast.makeText(this, "Branch = " + branchName, Toast.LENGTH_SHORT).show();
    }
    private void setOnClickListener() {
        listener = new myAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("universityName", universityName);
                intent.putExtra("branchName", branchName);
                intent.putExtra("semNumber", semNumber[position]);
                startActivity(intent);
            }
        };
    }
}
