package com.chanapps.ranchan.app.views;

import android.content.Context;
import android.util.AttributeSet;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by johnarleyburns on 27/06/14.
 */
public class SquareNetworkImageView extends NetworkImageView {

        public SquareNetworkImageView(final Context context)
        {
            super(context);
        }

        public SquareNetworkImageView(final Context context, final AttributeSet attrs)
        {
            super(context, attrs);
        }

        public SquareNetworkImageView(final Context context, final AttributeSet attrs, final int defStyle)
        {
            super(context, attrs, defStyle);
        }


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
}
