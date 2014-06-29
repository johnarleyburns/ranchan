package com.chanapps.ranchan.app.views;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.chanapps.ranchan.app.R;

/**
 * Created by johnarleyburns on 27/06/14.
 */
public class ImageSizingWebView extends WebView {

    private int imagePaddingPx = 0;
    private int actionBarHeightPx = 0;
    private int boardTabletViewWidthPx = 0;
    private int fragmentMarginWidthPx = 0;
    private int maxHeaderScale = 1;

    private Point baseBox;
    private Point maxBox;
    private Point scaledBox;
    private float scale;

    public ImageSizingWebView(final Context context)
    {
        super(context);
    }

    public ImageSizingWebView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ImageSizingWebView(final Context context, final AttributeSet attrs, final int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void setImageSize(int width, int height) {
        baseBox = new Point(width, height);
        maxBox = new Point(maxWidth(), maxHeight());
        calcScaledBox();
        setScaledParams();
        setWebSettings();
    }

    private void setScaledParams() {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = scaledBox.x;
        params.height = scaledBox.y;
        setLayoutParams(params);
        setInitialScale(Math.round(scale * 100));
    }

    private void setWebSettings() {
        WebSettings settings = getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(false);
        setBackgroundColor(getContext().getResources().getColor(android.R.color.background_dark));
    }

    private void calcScaledBox() {
        scaledBox = new Point(baseBox.x * maxHeaderScale, baseBox.y * maxHeaderScale);
        //baseBox <= scaleBox <= maxBox;

        //Point scaledBox = new Point(baseBox.x, baseBox.y);
        if (scaledBox.x > maxBox.x) { // downscale to fix x in card
            scale = (float) maxBox.x / (float) scaledBox.x;
            scaledBox.x = (int) (scale * scaledBox.x);
            scaledBox.y = (int) (scale * scaledBox.y);
        }
        if (scaledBox.y > maxBox.y) { // downscale to fit y in card
            scale = (float) maxBox.y / (float) scaledBox.y;
            scaledBox.x = (int) (scale * scaledBox.x);
            scaledBox.y = (int) (scale * scaledBox.y);
        }
    }

    public int maxWidth() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        if (metrics == null) {
            return 0;
        }
        int naiveMax;

        naiveMax = metrics.widthPixels - imagePaddingPx - imagePaddingPx;
        naiveMax -= boardTabletViewWidthPx;
        return naiveMax;
    }

    public int maxHeight() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        if (metrics == null) {
            return 0;
        }
        int naiveMax;
        naiveMax = metrics.heightPixels - imagePaddingPx - imagePaddingPx - actionBarHeightPx;
        return naiveMax;
    }

              /*
            @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
        {
            final int width = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
            setMeasuredDimension(width, width);
        }

        @Override
        protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
        {
            super.onSizeChanged(w, w, oldw, oldh);
        }
            */
}
