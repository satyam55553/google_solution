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

public class TopicSelector extends AppCompatActivity {
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

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

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

                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition)
                                + " -> " + topicselected, Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(getApplicationContext(), ContributeActivity.class);
                intent.putExtra("topicCodeContributor", topicCode);
                intent.putExtra("topicSelected",topicselected);
                setResult(2,intent);
                finish();

                return true;
            }
        });
    }
}
