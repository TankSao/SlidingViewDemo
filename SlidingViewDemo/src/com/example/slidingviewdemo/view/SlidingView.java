package com.example.slidingviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.slidingviewdemo.R;
import com.example.slidingviewdemo.util.ScreenUtil;

public class SlidingView  extends HorizontalScrollView{
	private int viewWidth;
	private int slideViewWidth;

	private boolean isRightSliding;
	private boolean isLeftSliding;

	private boolean once;

	private ViewGroup leftView;
	private ViewGroup midView;
	private ViewGroup rightView;
	private ViewGroup parentView;

	private boolean isLeftViewShow;
	private boolean isRightViewShow;
	private int type;
	public interface OnViewShowListener
	{
		/**
		 * 
		 * @param isShow
		 *            true显示，false隐藏
		 * @param flag
		 *            0 左侧:?1右侧
		 */
		void onViewShow(boolean isShow, int flag);
	}

	public OnViewShowListener listener;

	public void setOnViewShowListener(OnViewShowListener onViewShowListener)
	{
		this.listener = onViewShowListener;
	}

	public SlidingView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);

	}

	/**
	 * 屏幕宽度
	 */
	private int mScreenWidth;

	/**
	 * dp 菜单距离屏幕的右边距
	 */
	private int mMenuRightPadding;

	public SlidingView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mScreenWidth = ScreenUtil.getScreenWidth(context);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingView, defStyle, 0);
		type = a.getInt(R.styleable.SlidingView_slideType, 2);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.SlidingView_slidePadding:
				// 默认50
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50f,
								getResources().getDisplayMetrics()));// 默认�?0DP
				break;
			}
		}
		a.recycle();
	}

	public SlidingView(Context context)
	{
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		if (!once)
		{

			parentView = (LinearLayout) getChildAt(0);
			leftView = (ViewGroup) parentView.getChildAt(0);
			midView = (ViewGroup) parentView.getChildAt(1);
			rightView = (ViewGroup) parentView.getChildAt(2);

			viewWidth = mScreenWidth - mMenuRightPadding;
			slideViewWidth = viewWidth / 2;
			if(type == 1){
				leftView.getLayoutParams().width = 0;
			}else{
				leftView.getLayoutParams().width = viewWidth;
			}
			midView.getLayoutParams().width = mScreenWidth;
			if(type == 0){
				rightView.getLayoutParams().width = 0;
			}else{
				rightView.getLayoutParams().width = viewWidth;
			}

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			// 隐藏?
			if(type == 1){
				this.scrollTo(0, 0);
			}else{
				this.scrollTo(viewWidth, 0);
			}
			once = true;
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
		// Up时，进行判断，如果显示区域大于视图宽度一半则完全显示，否则隐藏?
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			// 如果是操作左侧视图?
			if (isLeftSliding)
			{
				// 如果影藏的区域大于视图一半，则影藏视图?
				if (scrollX > slideViewWidth)
				{
					this.smoothScrollTo(viewWidth, 0);
					// 如果当前左侧视图是显示状态，且mOnMenuOpenListener不为空，则回调关闭菜�?
					if (isLeftViewShow && listener != null)
					{
						// 第一个参数true：打�?��单，false：关闭菜�?第二个参�?0 代表左侧�?代表右侧
						listener.onViewShow(false, 0);
					}
					isLeftViewShow = false;

				} else
				// 关闭左侧菜单
				{
					this.smoothScrollTo(0, 0);
					// 如果当前左侧菜单是关闭状态，且mOnMenuOpenListener不为空，则回调打�?���?
					if (!isLeftViewShow && listener != null)
					{
						listener.onViewShow(true, 0);
					}
					isLeftViewShow = true;
				}
			}

			// 操作右侧
			if (isRightSliding)
			{
				// 打开右侧侧滑菜单
				if (scrollX > slideViewWidth + viewWidth)
				{
					this.smoothScrollTo(viewWidth + viewWidth, 0);
				} else
				// 关闭右侧侧滑菜单
				{
					this.smoothScrollTo(viewWidth, 0);
				}
			}

			return true;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);

		if (l > viewWidth)
		{
			isRightSliding = true;
			isLeftSliding = false;

		} else
		{
			isRightSliding = false;
			isLeftSliding = true;

		}
	}

}
