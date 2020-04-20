package com.example.surprise;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;

import java.net.HttpURLConnection;
import java.net.URL;

public class tryCon extends AsyncTask {
    private WebView webView;
    private String url;
    private String googleUrl;

    @Override
    protected Integer doInBackground(Object[] objects) {
        webView = (WebView) objects[0];
        url = (String) objects[1];
        googleUrl = (String) objects[2];

        try {
            HttpURLConnection.setFollowRedirects(false);
            URL tryUrl;
            tryUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) tryUrl.openConnection();
            int response = con.getResponseCode();
            con.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object response) {
        super.onPostExecute(response);
        if (response != null) {
            if ((int)response == HttpURLConnection.HTTP_OK) {
                webView.loadUrl(url);
            } else {
                webView.loadUrl(googleUrl);
            }
        } else {
            webView.loadUrl(googleUrl);
        }
    }
}
