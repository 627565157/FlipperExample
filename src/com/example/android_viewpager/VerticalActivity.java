package com.example.android_viewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class VerticalActivity extends Activity {

	private ViewFlipper flipper;
	private float startY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vertical);
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.addView(getImageView(R.drawable.pic1));
		flipper.addView(getImageView(R.drawable.pic2));
		flipper.addView(getImageView(R.drawable.pic3));
		flipper.addView(getImageView(R.drawable.pic4));
		flipper.setBackgroundColor(Color.BLACK);
	}

	private ImageView getImageView(int resId) {
		ImageView image = new ImageView(this);
		image.setBackgroundResource(resId);
		return image;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			startY = event.getY();
			break;
		}

		case MotionEvent.ACTION_UP: {
			// 向下滑动
			if (event.getY() - startY > 50) {
				if (flipper.getDisplayedChild() > 0) {
					flipper.setInAnimation(this, R.anim.top_in);
					flipper.setOutAnimation(this, R.anim.top_out);
					flipper.showPrevious();
				} else {
					Toast.makeText(getApplicationContext(), "已经是第一页了",
							Toast.LENGTH_SHORT).show();
				}

			}
			// 向上滑动
			if (startY - event.getY() > 50) {
				if (flipper.getDisplayedChild() < (flipper.getChildCount() - 1)) {
					flipper.setInAnimation(this, R.anim.bottom_in);
					flipper.setOutAnimation(this, R.anim.bottom_out);
					flipper.showNext();
				} else {
					Toast.makeText(getApplicationContext(), "已经是最后一页了",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		}
		}
		return super.onTouchEvent(event);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vertical, menu);
		return true;
	}

}
