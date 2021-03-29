package com.example.gsc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContributeActivity extends AppCompatActivity {
    Spinner universityList, courseList, branchList, semesterList, subjectList;
    String university, course, branch, subject, module, topic, url, topicCode;
    int semester;
    Button contriBtn,youTubeBtn;
    TextView videoId, topicSelected;
    private static final String TAG = "ContributeActivity";
    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mMessagesDatabaseReference;
    public static final String ANONYMOUS = "anonymous";
    public static final int TOPIC_REQ = 2;
    private String mUsername, key = "";
    String[] spinnerArray;
    HashMap<Integer, String> spinnerMap;
    boolean isPresent=false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TOPIC_REQ) {
            if(data!=null){
                topic = data.getStringExtra("topicSelected");
                topicCode = data.getStringExtra("topicCodeContributor");
                topicSelected.setText("" + topic);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

        mFirebasedatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebasedatabase.getReference().child("videoUrl");
        mUsername = ANONYMOUS;

        universityList = (Spinner) findViewById(R.id.spinner_university);
        courseList = (Spinner) findViewById(R.id.spinner_course);
        branchList = (Spinner) findViewById(R.id.spinner_branch);
        semesterList = (Spinner) findViewById(R.id.spinner_semester);
        subjectList = (Spinner) findViewById(R.id.spinner_subject);
        topicSelected = (TextView) findViewById(R.id.contribute_topic);
        videoId = (TextView) findViewById(R.id.contribute_videoId);
        contriBtn = (Button) findViewById(R.id.contribute_submit);
        youTubeBtn = (Button) findViewById(R.id.contribute_youtube);

        setupSpinner(R.array.university_array, universityList);
        setupSpinner(R.array.courses_array, courseList);
        setupSpinner(R.array.branch_array, branchList);
        setupSpinner(R.array.semester_array, semesterList);
        setupSpinner(R.array.subject_array, subjectList);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString(Intent.EXTRA_TEXT);
            videoId.setText("" + url);
        }

        contriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topicCode!=null && videoId.getText()!=getString(R.string.open_youtube)){
                    university = universityList.getSelectedItem().toString();
                    course = courseList.getSelectedItem().toString();
                    branch = branchList.getSelectedItem().toString();
                    semester = Integer.parseInt(semesterList.getSelectedItem().toString());
                    subject = subjectList.getSelectedItem().toString();
                    key = VideoData.createKey(university, course, branch, semester, subject, topicCode);
                    isPresent=false;
                    queryFirebase(key);
                    if(isPresent){
                        VideoData videoData = new VideoData(key, url);
                        mMessagesDatabaseReference.push().setValue(videoData);
                        Toast.makeText(ContributeActivity.this,
                                "Video was Submitted :)", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ContributeActivity.this,
                                "Video already exist", Toast.LENGTH_SHORT).show();
                    }

                }else if(topicCode==null){
                    topicSelected.setError("Select your Topic");
                    Toast.makeText(ContributeActivity.this,
                            "Select the Topic!", Toast.LENGTH_SHORT).show();
                }else if(videoId.getText()==getString(R.string.open_youtube)){
                    videoId.setError(getString(R.string.open_youtube));
                }
            }
        });

        topicSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContributeActivity.this, TopicSelector.class);
                startActivityForResult(intent, TOPIC_REQ);
            }
        });

        youTubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(ContributeActivity.this, "YouTube app is not available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setupSpinner(int arrayId, Spinner list) {

        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(spinnerAdapter);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), selection + " selected", Toast.LENGTH_SHORT).show();
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void queryFirebase(String k){
        Query query = FirebaseDatabase.getInstance().getReference("videoUrl")
                .orderByChild("key")
                .equalTo(k);
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                VideoData videoData = dataSnapshot.getValue(VideoData.class);
                assert videoData != null;
                String k = videoData.getKey();
                if (k != null) {
                    if(k==key){
                        isPresent=true;
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}
