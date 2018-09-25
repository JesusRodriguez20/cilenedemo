package msp.cilenedemo.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import msp.cilenedemo.R;

public class ActivityInformacionGeneral extends AppCompatActivity {
    private LinearLayout linearLayout;
    private ImageView imageView1, imageView2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_informacion_general);
        this.setup();
        this.draw();
    }

    private void setup() {
        linearLayout = findViewById(R.id.activity_informacion_general_linear_layout);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle("Informacion");
        }
        imageView1 = findViewById(R.id.activity_informacion_general_image_view_1);
        imageView2 = findViewById(R.id.activity_informacion_general_image_view_2);
        textView = findViewById(R.id.activity_informacion_general_text_view);
    }

    @SuppressLint("SetTextI18n")
    private void draw() {
        linearLayout.setBackgroundColor(Color.WHITE);

        imageView1.setImageResource(R.drawable.ic_launcher_background);

        textView.setGravity(Gravity.START | Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
        textView.setTextColor(Color.GRAY);
        textView.setText(
                "La tecnología beacons últimamente está en boca de todos los especialistas en marketing. Y son pequeños dispositivos (del tamaño de una moneda) que emiten señales de onda corta a través de Bluetooth Low Energy (BLE), que pueden tener un alcance de hasta 70mts, estas señales son captadas una App instalada en el smartphone permitiendo presentar información de todo tipo, desde descuentos hasta geolocalización, pasando por recomendaciones personalizadas." +
                "\n" +
                "\n" +
                "\n" +
                "Cilene es el nombre comercial con el que hemos denominado nuestra plataforma basada en Beacons para el sector comercial e industrial.");

        imageView2.setImageResource(R.drawable.ic_launcher_background);
    }
}
