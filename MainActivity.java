package com.example.titanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.HashSet;
import  	java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity  {
    private SensorManager mSensorManager = null;
    private CanvasView customCanvas;
    private Sensor mAccelerometer = null;
    static float xCircle, yCircle;
    static HashSet<iceberg> iceberg = new HashSet<iceberg>();

    public static HashSet<com.example.titanic.iceberg> getIceberg() {
        return iceberg;
    }




    final SensorEventListener mSensorEventListener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

            // Que faire en cas de changement de précision ?

        }


        public void onSensorChanged(SensorEvent sensorEvent) {



            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                xCircle =xCircle+ (sensorEvent.values[0])*-10;

                Log.d("Sensors", "Rotation sur l'axe x : " + xCircle);

                Log.d("Sensors", "Rotation sur l'axe y : " + yCircle);

                customCanvas.moveCircle(500+xCircle,1000);

            }



        }

    };


    @Override

    public final void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        /*xCircle=0;
        yCircle=0;*/

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.
            iceberg.add(new iceberg((float)Math.random() * ( 1000 - 0 )));

            for (iceberg ice : iceberg)
            {
                ice.setY(ice.getY()+1);
                if(ice.getY()>1500)
                    iceberg.remove(ice);
                customCanvas.drawIce(ice.getX(),ice.getY());
            }
            //Do something to the UI thread here

        }
    };

    @Override

    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override

    protected void onPause() {

        super.onPause();

        mSensorManager.unregisterListener(mSensorEventListener, mAccelerometer);

    }

}

