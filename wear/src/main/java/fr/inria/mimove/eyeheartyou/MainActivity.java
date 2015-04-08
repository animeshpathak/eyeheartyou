package fr.inria.mimove.eyeheartyou;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView mTextView;

    private Sensor mHeartRateSensor;
    private SensorManager mSensorManager;

    private static final String TAG = "EyeHeartYou";

    public MainActivity(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"going to register!");
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG,"going to unregister!");
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mTextView.setText("break free!!");
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i(TAG, "--------------------------");
        //Log.i(TAG, msg);
        Log.i(TAG, ""+ sensorEvent.sensor.getType());
        Log.i("live","--------------");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
