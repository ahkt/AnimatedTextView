package com.itsaky.animatedtext;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;

import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

public class AnimatedTextView extends View
{
    Paint paint;
    float length;
	TypedArray ta;
	int midWidth, midHeight;
	
	private Typeface mTypeface;
	private int color, strokeWidth;
	private float textSize;
	private String text;
	private ObjectAnimator animator;
	
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

	public void setMTypeface(Typeface mTypeface)
	{
		this.mTypeface = mTypeface;
		invalidate();
	}

	public Typeface getMTypeface()
	{
		return mTypeface;
	}

	public void setColor(int color)
	{
		this.color = color;
		invalidate();
	}

	public int getColor()
	{
		return color;
	}

	public void setStrokeWidth(int strokeWidth)
	{
		this.strokeWidth = strokeWidth;
		invalidate();
	}

	public int getStrokeWidth()
	{
		return strokeWidth;
	}

	public void setTextSize(float textSize)
	{
		this.textSize = textSize;
		invalidate();
	}

	public void setText(String text)
	{
		this.text = text;
		invalidate();
	}

    public void init(AttributeSet attr)
    {
		if(attr == null)return;
		
		Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		ta = getContext().obtainStyledAttributes(attr, R.styleable.AnimatedTextView);
		color = ta.getColor(R.styleable.AnimatedTextView_atv_color, Color.parseColor("#f44336"));
		strokeWidth = ta.getDimensionPixelSize(R.styleable.AnimatedTextView_atv_strokeWidth, 8);
		String font = ta.getString(R.styleable.AnimatedTextView_atv_font);
		
		mTypeface =  null;
		paint = new Paint();
		if(!font.isEmpty())
		{
			mTypeface = FontManager.getInstance(getContext()).getFont(font);
			if(mTypeface != null) paint.setTypeface(mTypeface);
		}
		
		paint.setAntiAlias(true);
		paint.setColor(color);
		paint.setStyle(Paint.Style.STROKE);
		paint.setTextSize(getTextSize());
		paint.setStrokeWidth(strokeWidth);

		midWidth = width/2;
		midHeight = height/2;

        animator = ObjectAnimator.ofFloat(AnimatedTextView.this, "phase", 1.0f, 0.0f);
        animator.setDuration(8000);
        animator.start();
    }

	private float getTextSize()
	{
		textSize = ta.getDimension(R.styleable.AnimatedTextView_atv_textSize, 100);
		return textSize;
	}

    public void setPhase(float phase)
    {
		paint.setPathEffect(createPathEffect(2000, phase, 0.0f));
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
		drawCenterText(c, paint, getText().toString());
    }
	
	private String getText()
	{
		text = ta.getString(R.styleable.AnimatedTextView_atv_text);
		return text;
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
		//setText(null);
	}
}
