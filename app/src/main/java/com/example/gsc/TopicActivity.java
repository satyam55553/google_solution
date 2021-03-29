package com.example.gsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import androidx.annotation.NonNull;

public class TopicActivity extends YouTubeBaseActivity {
    TextView txtVdoID;
    Button btnPlay;
    ProgressBar progressBar;
    YouTubePlayerView mYouTubeView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    String topicCode = "Not selected", subName = "Not selected", semNumber = "Not selected",
            branchName = "Not selected", courseName = "Not selected", universityName = "Not selected";
    String url = "", key = "";
    String videoId = "";
    String URL_FORMAT = "https://youtu.be/";

    private FirebaseDatabase mFirebasedatabase;
    private DatabaseReference mMessagesDatabaseReference;

    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        mFirebasedatabase = FirebaseDatabase.getInstance();

        mMessagesDatabaseReference = mFirebasedatabase.getReference().child("videoUrl");
        mUsername = ANONYMOUS;
        progressBar=(ProgressBar) findViewById(R.id.progress_bar);
        txtVdoID = (TextView) findViewById(R.id.textVideoId);
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        mYouTubeView = (YouTubePlayerView) findViewById(R.id.view);
        btnPlay.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            universityName = extras.getString("universityName");
            courseName = extras.getString("courseName");
            branchName = extras.getString("branchName");
            semNumber = extras.getString("semNumber");
            subName = extras.getString("subName");
            topicCode = extras.getString("topicCode");

            key = VideoData.createKey(universityName, courseName, branchName,
                    Integer.parseInt(semNumber), subName,topicCode);
//            Toast.makeText(getApplicationContext(),"key="+key,Toast.LENGTH_SHORT).show();
        }

        queryFirebase();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(videoId);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                };

                mYouTubeView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);
            }
        });
    }

    private void queryFirebase(){
        Query query = FirebaseDatabase.getInstance().getReference("videoUrl")
                .orderByChild("key")
                .equalTo(key);
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                VideoData videoData = dataSnapshot.getValue(VideoData.class);
                assert videoData != null;
                url = videoData.getUrl();
                if (url != null) {
                    if (url.startsWith(URL_FORMAT)) {
                        videoId = url.substring(17);
                        txtVdoID.setText("video ID= " + videoId);
                        progressBar.setVisibility(View.GONE);
                        btnPlay.setEnabled(true);
                    }
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}
