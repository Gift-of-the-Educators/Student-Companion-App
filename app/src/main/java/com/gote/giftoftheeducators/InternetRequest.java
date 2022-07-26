package com.gote.giftoftheeducators;

import android.app.Activity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InternetRequest {
    OkHttpClient client = new OkHttpClient();

    public void doRequest(String url, Activity activity, RequestHandler requestHandler){
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestHandler.processResponse(responseData);
                    }
                });
            }
        });
    }
}
