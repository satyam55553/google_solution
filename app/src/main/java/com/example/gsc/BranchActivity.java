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

public class BranchActivity extends AppCompatActivity {
    String courseName = "Not selected", universityName = "Not selected";
    String[] branchNames;
    private myAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        branchNames = new String[]{getString(R.string.CO), getString(R.string.IT),
                getString(R.string.ET), getString(R.string.EL), getString(R.string.IN)};
        TextView head=findViewById(R.id.main_header);
        head.setText(R.string.select_branch);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        setOnClickListener();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new myAdapter(branchNames, listener));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universityName = extras.getString("universityName");
            courseName = extras.getString("courseName");
        }
//        Toast.makeText(this, "Course = " + courseName, Toast.LENGTH_SHORT).show();
    }

    private void setOnClickListener() {
        listener = new myAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), SemesterActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("universityName", universityName);
                intent.putExtra("branchName", branchNames[position]);
                startActivity(intent);
            }
        };
    }
}
