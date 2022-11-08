package edu.temple.sensordata;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];//();
    private float timestamp;




    SensorManager sensorManager;

    Sensor accel_sensor;
    Sensor gyro_sensor;

    SensorEventListener sensorEventListener;

    TextView xData, yData, zData;

    boolean playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xData = findViewById(R.id.xData);
        yData = findViewById(R.id.yData);
        zData = findViewById(R.id.zData);

        sensorManager = getSystemService(SensorManager.class);

        assert sensorManager != null;
        accel_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);



        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
                    //Update UI

                    xData.setText(String.valueOf(event.values[0]));
                    yData.setText(String.valueOf(event.values[1]));
                    zData.setText(String.valueOf(event.values[2]));


                }
                if(event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
                    //UpdateUI
                }





            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };

    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(sensorEventListener, accel_sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }


}
