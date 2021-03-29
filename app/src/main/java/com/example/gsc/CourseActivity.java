package com.example.gsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CourseActivity extends AppCompatActivity {
    String universityName = "Not selected";
    String[] courseName;
    private myAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        TextView head=findViewById(R.id.main_header);
        head.setText(R.string.select_course);
        courseName = new String[]{getString(R.string.BE)};
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        setOnClickListener();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new myAdapter(courseName, listener));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universityName = extras.getString("universityName");
        }
//        Toast.makeText(this, "University = " + universityName, Toast.LENGTH_SHORT).show();
    }

    private void setOnClickListener() {
        listener = new myAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), BranchActivity.class);
                intent.putExtra("courseName", courseName[position]);
                intent.putExtra("universityName", universityName);
                startActivity(intent);
            }
        };
    }
}
