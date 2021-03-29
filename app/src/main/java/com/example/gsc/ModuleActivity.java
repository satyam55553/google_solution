package com.example.gsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ModuleActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    String subName = "Not selected",semNumber = "Not selected",branchName = "Not selected",
            courseName = "Not selected",universityName = "Not selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail =(HashMap<String, List<String>>) ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universityName=extras.getString("universityName");
            courseName = extras.getString("courseName");
            branchName = extras.getString("branchName");
            semNumber = extras.getString("semNumber");
            subName = extras.getString("subName");
        }
//        Toast.makeText(this, "Subject = " + subName, Toast.LENGTH_SHORT).show();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String topicselected=expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition);

                String topicCode=String.valueOf(groupPosition)+String.valueOf(childPosition);

//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> " + topicselected, Toast.LENGTH_SHORT
//                ).show();

                Intent intent = new Intent(getApplicationContext(), TopicActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("universityName", universityName);
                intent.putExtra("branchName", branchName);
                intent.putExtra("semNumber", semNumber);
                intent.putExtra("subName", subName);
                intent.putExtra("topicCode", topicCode);
                startActivity(intent);

                return true;
            }
        });
    }
}
