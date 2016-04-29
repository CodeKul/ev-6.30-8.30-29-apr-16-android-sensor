package com.codekul.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listSensors = manager.getSensorList(Sensor.TYPE_ALL);
        final TextView textView = (TextView) findViewById(R.id.textView);
        for (Sensor sensor : listSensors) {
            Log.i("@codekul",sensor.getName());
            textView.append("\n"+sensor.getName());
        }

        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sensor == null){
            textView.setText("No Sensor Found");
            return;
        }


        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                textView.setText("LIght "+event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

                textView.append("Accuracy "+accuracy);
            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
