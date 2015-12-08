package com.example.android_viewpager;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class HorizontalStartActivity extends Activity {

	private ViewFlipper flipper;
	private float startX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_horizontal_start);
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
			startX = event.getX();
			break;
		}

		case MotionEvent.ACTION_UP: {
			// 向右滑动
			if (event.getX() - startX > 50) {
				if (flipper.getDisplayedChild() > 0) {
					flipper.setInAnimation(this, R.anim.left_in);
					flipper.setOutAnimation(this, R.anim.left_out);
					flipper.showPrevious();
				} else {
					Toast.makeText(getApplicationContext(), "已经是第一页了",
							Toast.LENGTH_SHORT).show();
				}

			}
			// 向左滑动
			if (startX - event.getX() > 50) {
				if (flipper.getDisplayedChild() < (flipper.getChildCount() - 1)) {
					flipper.setInAnimation(this, R.anim.right_in);
					flipper.setOutAnimation(this, R.anim.right_out);
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
		getMenuInflater().inflate(R.menu.horizontal_start, menu);
		return true;
	}

}
