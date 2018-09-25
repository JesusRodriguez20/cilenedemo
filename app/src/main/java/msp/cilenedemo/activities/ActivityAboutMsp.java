package msp.cilenedemo.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import msp.cilenedemo.R;

public class ActivityAboutMsp extends AppCompatActivity {
    private LinearLayout linearLayout;
    private ImageView imageView1, imageView2;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_about_msp);
        this.setup();
        this.draw();
    }

    private void setup() {
        linearLayout = findViewById(R.id.activity_about_msp_linear_layout);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle("About MSP");
        }
        imageView1 = findViewById(R.id.activity_about_msp_image_view_1);
        imageView2 = findViewById(R.id.activity_about_msp_image_view_2);
        textView1 = findViewById(R.id.activity_about_msp_text_view_1);
        textView2 = findViewById(R.id.activity_about_msp_text_view_2);
    }

    @SuppressLint("SetTextI18n")
    private void draw() {
        linearLayout.setBackgroundColor(Color.WHITE);

        imageView1.setImageResource(R.drawable.ic_launcher_background);

        textView1.setGravity(Gravity.CENTER);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView1.setTextColor(Color.GRAY);
        textView1.setText(
                "© CopyRight 2017 All Right Reserved in\n" +
                "USA, México and other countries\n" +
                "MSP Mobility Corporation");

        textView2.setGravity(Gravity.CENTER);
        textView2.setPaintFlags(textView2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView2.setTextColor(Color.BLUE);
        textView2.setText("www.mspmovil.com");
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://mspmovil.com/home/"));
                startActivity(intent);
            }
        });

        imageView2.setImageResource(R.drawable.ic_launcher_background);
    }
}
