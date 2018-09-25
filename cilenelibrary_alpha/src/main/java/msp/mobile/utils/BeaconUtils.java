package msp.mobile.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeaconUtils {
    public static final String ALTBEACON = "m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";
    public static final String EDDYSTONE_TLM = "x,s:0-1=feaa,m:2-2=20,d:3-3,d:4-5,d:6-7,d:8-11,d:12-15";
    public static final String EDDYSTONE_UID = "s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19";
    public static final String EDDYSTONE_URL = "s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-20v";
    public static final String IBEACON = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
    public static final String SERVER_BASE = "http://tfinder.redirectme.net/MSPIot/SendInfoBeacon.aspx?valor=";

    public static String urlSerial() {
        return "01/017671|";
    }

    public static String stringSerial() {
        return "01/017672|";
    }

    public static String bitmapSerial() {
        return "01/017673|";
    }

    public static String minor() {
        return "10|";
    }

    public static String major() {
        return "5|";
    }

    public static String range() {
        return "21|";
    }

    public static String battery() {
        return "56|";
    }

    @SuppressLint("SimpleDateFormat")
    public static String date() {
        return new SimpleDateFormat("dd/MM/yyyy|hh:mm").format(new Date());
    }
    /*
    listo , en la cadena que mandas al servidor para consumir el geten
     la parte del numero de serio que se obtiene del beacon debes irlo
      cambiando para que te regrese lo que neceistes

      el numero de serie 01/017671 te regresara una cadena de texto ( ya la actualixce )
      el numero de serie 01/017672 te regresara una url ( puse la pagina de msp )
      el numero de serie 01/017673 te regresa la url de una imagen de un cuponcon

       eso ya puedes hacer las 3 pruebas
     */
}
