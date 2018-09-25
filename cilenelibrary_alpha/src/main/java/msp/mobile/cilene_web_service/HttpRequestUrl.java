package msp.mobile.cilene_web_service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import msp.mobile.utils.BeaconUtils;

public class HttpRequestUrl extends AsyncTask<String, Integer, String> {
    private HttpRequestUrlInterface httpRequestUrlInterface;

    public HttpRequestUrl(HttpRequestUrlInterface httpRequestUrlInterface) {
        super();
        this.httpRequestUrlInterface = httpRequestUrlInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        return this.requestHttpResponse(
                BeaconUtils.SERVER_BASE +
                        BeaconUtils.urlSerial() +
                        BeaconUtils.minor() +
                        BeaconUtils.major() +
                        BeaconUtils.range() +
                        BeaconUtils.battery() +
                        BeaconUtils.date());
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        httpRequestUrlInterface.processFinish(result);
    }

    private String requestHttpResponse(String uri) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            switch (httpURLConnection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    bufferedReader.close();
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    result.append("server not found!");
                    break;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public interface HttpRequestUrlInterface {
        void processFinish(String output);
    }
}
