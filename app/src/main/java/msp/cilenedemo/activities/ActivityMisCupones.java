package msp.cilenedemo.activities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import msp.cilenedemo.R;
import msp.mobile.cilene_web_service.HttpRequestBitmap;
import msp.mobile.cilene_web_service.HttpRequestString;
import msp.mobile.cilene_web_service.HttpRequestUrl;

public class ActivityMisCupones extends AppCompatActivity {
    private LinearLayout linearLayout;
    private Button button;
    private TextView textView1, textView2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_mis_cupones);
        this.setup();
        this.draw();
    }

    private void setup() {
        linearLayout = findViewById(R.id.activity_mis_cupones_linear_layout);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle("Mis cupones");
        }

        button = new Button(this);
        textView1 = new TextView(this);
        textView2 = new TextView(this);
        imageView = new ImageView(this);
    }

    @SuppressLint("SetTextI18n")
    private void draw() {
        linearLayout.setBackgroundColor(Color.WHITE);

        textView1.setMinimumWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        textView1.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        textView1.setTextColor(Color.GRAY);
        textView1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textView1.setPadding(0, 20, 0, 0);

        textView2.setMinimumWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        textView2.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        textView2.setTextColor(Color.GRAY);
        textView2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textView2.setPadding(0, 20, 0, 0);

        imageView.setMinimumWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setPadding(0, 20, 0, 0);

        button.setMinimumWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        button.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setTextColor(Color.BLUE);
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setText("Prueba de servidor");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpRequestUrl(new HttpRequestUrl.HttpRequestUrlInterface() {
                    @Override
                    public void processFinish(String output) {
                        textView1.setText(output);
                    }
                }).execute();

                new HttpRequestString(new HttpRequestString.HttpRequestStringInterface() {
                    @Override
                    public void processFinish(String output) {
                        textView2.setText(output);
                    }
                }).execute();

                new HttpRequestBitmap(new HttpRequestBitmap.HttpRequestBitmapInterface() {
                    @Override
                    public void processFinish(Bitmap output) {
                        imageView.setImageBitmap(output);
                    }
                }).execute();
            }
        });

        linearLayout.addView(button);
        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(imageView);
    }
}
