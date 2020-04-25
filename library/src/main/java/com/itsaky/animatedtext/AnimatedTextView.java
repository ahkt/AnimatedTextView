package com.itsaky.animatedtext;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedTextView extends View
{
    private Paint mPaint;
	private TypedArray mTypedArray;
	
	private Typeface mTypeface;
	private int mColor, mStrokeWidth;
	private float mTextSize;
	private String mText;
	private int mDuration;
	
	private ObjectAnimator mAnimator;
	
    public AnimatedTextView(Context context)
    {
        super(context);
		init(null);
    }

    public AnimatedTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
		init(attrs);
    }

    public AnimatedTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
		init(attrs);
    }

	public void setTypeface(Typeface mTypeface)
	{
		this.mTypeface = mTypeface;
	}

	public Typeface getTypeface()
	{
		return mTypeface;
	}

	public void setColor(int mColor)
	{
		this.mColor = mColor;
	}

	public int getColor()
	{
		return mColor;
	}

	public void setStrokeWidth(int mStrokeWidth)
	{
		this.mStrokeWidth = mStrokeWidth;
	}

	public int getStrokeWidth()
	{
		return mStrokeWidth;
	}

	public void setTextSize(float mTextSize)
	{
		this.mTextSize = mTextSize;
	}

	public float getTextSize()
	{
		return mTextSize;
	}

	public void setText(String mText)
	{
		this.mText = mText;
	}

	public String getText()
	{
		return mText;
	}

	public void setDuration(int mDuration)
	{
		this.mDuration = mDuration;
	}

	public int getDuration()
	{
		return mDuration;
	}

	public void init(AttributeSet attr)
    {
		mPaint = new Paint();
		mTypeface =  Typeface.MONOSPACE;
		mColor = Color.parseColor("#f44336");
		mStrokeWidth = 10;
		mText = "Akash Yadav";
		mTextSize = 150;
		mDuration = 5000;
		
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		
		if(attr != null)
		{
			mTypedArray = getContext().obtainStyledAttributes(attr, R.styleable.AnimatedTextView);
			mColor = mTypedArray.getColor(R.styleable.AnimatedTextView_atv_color, mColor);
			mStrokeWidth = mTypedArray.getDimensionPixelSize(R.styleable.AnimatedTextView_atv_strokeWidth, mStrokeWidth);
			mText = mTypedArray.getText(R.styleable.AnimatedTextView_atv_text).toString();
			mTextSize = mTypedArray.getDimension(R.styleable.AnimatedTextView_atv_textSize, mTextSize);
			mDuration = mTypedArray.getInteger(R.styleable.AnimatedTextView_atv_duration, mDuration);
			String font = mTypedArray.getString(R.styleable.AnimatedTextView_atv_font);

			if(font != null)
			{
				mTypeface = FontManager.getInstance(getContext()).getFont(font);
				if(mTypeface != null) mPaint.setTypeface(mTypeface);
			}
		}
		
		start();
		
    }
	
	public void start()
	{
		mPaint.setColor(mColor);
		mPaint.setTextSize(mTextSize);
		mPaint.setStrokeWidth(mStrokeWidth);
		mPaint.setTypeface(mTypeface);
		mAnimator = ObjectAnimator.ofFloat(AnimatedTextView.this, "phase", 1.0f, 0.0f);
		mAnimator.setDuration(mDuration);

		if(!mAnimator.isRunning())
		{
			mAnimator.start();
		}
	}

    public void setPhase(float phase)
    {
		mPaint.setPathEffect(createPathEffect(mPaint.measureText(mText), phase, 0.0f));
        invalidate();
	}

    private static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
								  Math.max(phase * pathLength, offset));
    }

    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
		drawCenterText(c, mPaint, mText);
    }

	private Rect r = new Rect();

	private void drawCenterText(Canvas canvas, Paint paint, String text) {
		canvas.getClipBounds(r);
		int cHeight = r.height();
		int cWidth = r.width();
		paint.setTextAlign(Paint.Align.LEFT);
		paint.getTextBounds(text, 0, text.length(), r);
		float x = cWidth / 2f - r.width() / 2f - r.left;
		float y = cHeight / 2f + r.height() / 2f - r.bottom;
		canvas.drawText(text, x, y + 200, paint);
	}
}
