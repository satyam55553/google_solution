package com.example.gsc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectActivity extends AppCompatActivity {
    String semNumber = "Not selected", branchName = "Not selected",
            courseName = "Not selected", universityName = "Not selected";
    String[] subjects;
    CheckBox checkBox;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    private myAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        getSupportActionBar().hide();

        subjects = new String[]{getString(R.string.ESRTOS),
                "Sub 2", "Sub 3", "Sub 4", "Sub 5", "Sub 6"};
//        checkBox=(CheckBox) findViewById(R.id.checkboxHome);

        TextView head = findViewById(R.id.main_header);
        head.setText(R.string.select_subject);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universityName = extras.getString("universityName");
            courseName = extras.getString("courseName");
            branchName = extras.getString("branchName");
            semNumber = extras.getString("semNumber");

//            SharedPreferences.Editor editor = sharedpreferences.edit();
//
//            editor.putString("universityName", universityName);
//            editor.putString("courseName", courseName);
//            editor.putString("branchName", branchName);
//            editor.putString("semNumber", semNumber);
//            editor.putBoolean("homescreen", true);
//            editor.commit();
//            Toast.makeText(this, "Got your preferences", Toast.LENGTH_SHORT).show();
        }

//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                if(b){
//                    editor.putBoolean("homescreen", b);
//                    editor.putString("universityName", universityName);
//                    editor.putString("courseName", courseName);
//                    editor.putString("branchName", branchName);
//                    editor.putString("semNumber", semNumber);
//                    editor.commit();
//                }else{
//                    editor.putBoolean("homescreen", b);
//                }
//            }
//        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        setOnClickListener();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new myAdapter(subjects, listener));
    }

    private void setOnClickListener() {
        listener = new myAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), ModuleActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("universityName", universityName);
                intent.putExtra("branchName", branchName);
                intent.putExtra("semNumber", semNumber);
                intent.putExtra("subName", subjects[position]);
                startActivity(intent);
            }
        };
    }
}
