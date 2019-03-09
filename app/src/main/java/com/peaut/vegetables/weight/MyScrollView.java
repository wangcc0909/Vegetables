package com.peaut.vegetables.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author peaut
 * @package com.peaut.vegetables.weight
 * @fileName MyScrollView
 * @date on  2019/3/8  10:52
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    private OnScrollViewListener mListener;

    public interface OnScrollViewListener {
        void onScrollChanged(int scrollX, int scrollY, int oldx, int oldY);
    }

    public void setOnScrollViewListener(OnScrollViewListener listener) {
        mListener = listener;
    }
}
