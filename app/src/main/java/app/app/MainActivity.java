package app.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.itsaky.animatedtext.AnimatedTextView;
import android.graphics.Typeface;
import android.text.style.LineHeightSpan;

public class MainActivity extends Activity 
{
	AnimatedTextView mt;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mt = new AnimatedTextView(this);
		((LinearLayout)findViewById(R.id.activitymainLinearLayout1)).addView(mt);
		invalid(null);
	}
	
	public void invalid(View v)
	{
		mt.setDuration(5000);
		mt.setColor(Color.BLACK);
		mt.setText("Mr.Developer");
		mt.setTextSize(150);
		mt.setStrokeWidth(8);
		mt.setTypeface(Typeface.SERIF);
		
		mt.start();
	}
}
