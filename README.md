# AnimatedText View
Simple library which draws Text with an Animation.

### Dowload 

[![](https://jitpack.io/v/itsaky/AnimatedTextView.svg)](https://jitpack.io/#itsaky/AnimatedTextView)


Add to project's build.gradle
```
maven { url 'https://jitpack.io' }
```

Add to module-level build.gradle
```
implementation 'com.github.itsaky:AnimatedTextView:<latest-version-here>'
```

### Usage

Add the view to the layout.
```xml
<com.itsaky.animatedtext.AnimatedTextView
		android:id="@+id/animatedTextView"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_below="@id/ic_vector"
		android:layout_centerInParent="true"
		app:atv_text="AnimatedTextView"
		app:atv_color="@android:color/white"
		app:atv_strokeWidth="1dp"
		app:atv_font="fonts/font_splash.ttf"
		app:atv_textSize="40sp"
		app:atv_duration="5000"/>
```
OR

Add dynamically
```java
AnimatedTextView mt = new AnimatedTextView(this);
mt.setDuration(5000); // in ms
mt.setColor(Color.BLACK);
mt.setText("Mr.Developer");
mt.setTextSize(150);
mt.setStrokeWidth(8);
mt.setTypeface(Typeface.SERIF);
	
mt.start(); // call this if you change any property dynamically
```

### Attributes

| Attribute     | Description                    |
|---------------|--------------------------------|
| atv_text      | Text to be shown in the view   |
| atv_textSize  | Specifies the size of the text |
| atv_color     | Color of the text to be drawn  |
| atv_strokeWidth | Stroke width of the text |
| atv_font | Font of the text to be drawn. The view animates the path of the text outlines. This font must be present in the 'assets' folder. If it is in assets/font/font.ttf, then provide the path as 'font/font.ttf' |
| atv_duration | Duration of the animation |

### Developer

##### Akash Yadav
* [Instagram](http://instagram.com/_mr_developer)
* [Telegram](http://t.me/itsaky)
