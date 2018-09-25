package msp.mobile.cilene_web_service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import msp.mobile.utils.BeaconUtils;

public class HttpRequestBitmap extends AsyncTask<String, Integer, Bitmap> {
    private HttpRequestBitmapInterface httpRequestBitmapInterface;

    public HttpRequestBitmap(HttpRequestBitmapInterface httpRequestBitmapInterface) {
        super();
        this.httpRequestBitmapInterface = httpRequestBitmapInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return this.requestHttpResponse(
                BeaconUtils.SERVER_BASE +
                        BeaconUtils.bitmapSerial() +
                        BeaconUtils.minor() +
                        BeaconUtils.major() +
                        BeaconUtils.range() +
                        BeaconUtils.battery() +
                        BeaconUtils.date());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        httpRequestBitmapInterface.processFinish(result);
    }

    private Bitmap requestHttpResponse(String uri) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new java.net.URL(uri).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public interface HttpRequestBitmapInterface {
        void processFinish(Bitmap output);
    }
}
