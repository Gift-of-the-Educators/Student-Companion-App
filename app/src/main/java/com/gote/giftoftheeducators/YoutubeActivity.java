package com.gote.giftoftheeducators;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YoutubeActivity extends YouTubeBaseActivity {

    String api_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video_list);

        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        api_key = appInfo.metaData.getString("youtubeAPIKey");

        SharedPreferences preferences = getSharedPreferences("UserPrefs", this.MODE_PRIVATE);
        initialize(preferences.getString("mode", "light"));

        InternetRequest youtube = new InternetRequest();
        youtube.doRequest("https://gote.org.za/GOTE_App/v1/getYoutubeVideos.php", this, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                processVideoList(response);
            }
        });
    }

    public void initialize(String mode) {
        LinearLayout main = findViewById(R.id.MainYoutubeListLayout);

        if(mode.equals("light")){
            main.setBackgroundColor(getResources().getColor(R.color.white));
        }
        else{
            main.setBackgroundColor(getResources().getColor(R.color.background_black));
        }
    }

    public void processVideoList(String response){
        LinearLayout videoLayout = findViewById(R.id.VideoLayout);

        try {
            JSONArray videoList = new JSONArray(response);
            for (int i = 0; i < videoList.length(); i++){
                JSONObject video = new JSONObject(videoList.getString(i));
                YoutubeThumbnailLayout thumbnailLayout = new YoutubeThumbnailLayout(this, videoLayout.getWidth());
                thumbnailLayout.setPadding(0, 8, 0, 0);

                thumbnailLayout.setTag(video.getString("youtube_url"));
                thumbnailLayout.videoName.setText(video.getString("youtube_name"));
                thumbnailLayout.thumbnail.initialize(api_key, new YouTubeThumbnailView.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                        try {
                            youTubeThumbnailLoader.setVideo(video.getString("youtube_url"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                        //Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                thumbnailLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YoutubeActivity.this, YoutubeVideoPlayerActivity.class);
                        intent.putExtra("youtube_video_url", thumbnailLayout.getTag().toString());
                        startActivity(intent);
                    }
                });

                videoLayout.addView(thumbnailLayout);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
