package cz.tokar.android.app.myarchitecture1.ui.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cz.tokar.android.app.myarchitecture1.R;

public class CustomButton extends RelativeLayout {
  LayoutInflater inflater = null;
  TextView tvButtonText;
  AppCompatImageView ivIcon;
  String mText;
  Drawable mIcon;
  ColorStateList mColor;

  public CustomButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initViews(attrs);
  }

  public CustomButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    initViews(attrs);
  }

  public CustomButton(Context context) {
    super(context);
    initViews(null);
  }

  void initViews(AttributeSet attrs) {
    inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.layout_button_row, this, true);
    ivIcon = findViewById(R.id.ivBtnIcon);
    tvButtonText = findViewById(R.id.tvBtnText);

    if (attrs != null) {
      TypedArray a = getContext().getTheme().obtainStyledAttributes(
        attrs,
        R.styleable.CustomButton,
        0, 0);

      try {
        mText = a.getString(R.styleable.CustomButton_cb_text);
        mIcon = a.getDrawable(R.styleable.CustomButton_cb_icon);
//        mColor = a.getColorStateList(R.styleable.CustomButton_cb_color);
        tvButtonText.setText(mText);
//        tvButtonText.setTextColor(mColor);

        if (mIcon != null) {
          ivIcon.setVisibility(VISIBLE);
          setIconDrawable(mIcon);
        } else {
          ivIcon.setVisibility(GONE);
        }

      } finally {
        a.recycle();
      }
    }
  }

  public void setText(String text) {
    tvButtonText.setText(text);
  }

  public void setIconDrawable(Drawable iconRes){
    ivIcon.setImageDrawable(iconRes);
  }

}