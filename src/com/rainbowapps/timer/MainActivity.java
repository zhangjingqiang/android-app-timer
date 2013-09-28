package com.rainbowapps.timer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button mStartButton;
	private Button mStopButton;
	private Button mResetButton;
	private Chronometer mChronometer;
	private long mTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Viewの初期化
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
		mStartButton = (Button) findViewById(R.id.start_button);
		mStopButton = (Button) findViewById(R.id.stop_button);
		mResetButton = (Button) findViewById(R.id.reset_button);
		mStopButton.setEnabled(false);
		// ボタンが押された時の処理
		mStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// スタート
				mStartButton.setEnabled(false);
				mStopButton.setEnabled(true);
				mChronometer.setBase(SystemClock.elapsedRealtime() - mTime);
				mChronometer.start();
			}
		});
		mStopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ストッフ
				mStartButton.setEnabled(true);
				mStopButton.setEnabled(false);
				mChronometer.stop();
				mTime = SystemClock.elapsedRealtime() - mChronometer.getBase();
			}
		});
		mResetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// リセット
				mStartButton.setEnabled(true);
				mStopButton.setEnabled(false);
				mChronometer.stop();
				mChronometer.setBase(SystemClock.elapsedRealtime());
				mTime = 0;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
