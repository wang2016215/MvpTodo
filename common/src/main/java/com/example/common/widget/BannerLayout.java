package com.example.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.common.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图组件
 * Created by TwoSX on 2016/3/30.
 */
public class BannerLayout extends RelativeLayout {

    private ViewPager mViewPager; // 轮播容器

    // 指示器（圆点）容器
    private LinearLayout indicatorContainer;

    private Drawable unSelectedDrawable;
    private Drawable selectedDrawable;


    private int WHAT_AUTO_PLAY = 1000;

    private boolean isAutoPlay = true; // 自动轮播

    private int itemCount;

    private int selectedIndicatorColor = 0xffff0000;
    private int unSelectedIndicatorColor = 0x88888888;
    private int titleBGColor = 0X55000000;
    private int titleColor = 0Xffffffff;

    private Shape indicatorShape = Shape.oval;
    private int selectedIndicatorHeight = 6;
    private int selectedIndecatorWidth = 6;
    private int unSelectedIndicatorHeight = 6;
    private int unSelectedIndecatorWidth = 6;

    private int autoPlayDuration = 6000;
    private int scrollDuration = 900;

    private int indicatorSpace = 3;
    private int indicatorMargin = 10;

    private static final int titlePadding = 20;

    private int defaultImage;

    private enum Shape {
        // 矩形 或 圆形的指示器
        rect, oval
    }

    private OnBannerItemClickListener mOnBannerItemClickListener;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                    mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
                }
            }
            return false;
        }
    });

    public BannerLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public BannerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BannerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.BannerLayoutStyle, defStyleAttr, 0);
        selectedIndicatorColor = array.getColor(R.styleable.BannerLayoutStyle_selectedIndicatorColor, selectedIndicatorColor);
        unSelectedIndicatorColor = array.getColor(R.styleable.BannerLayoutStyle_unSelectedIndicatorColor, unSelectedIndicatorColor);

        titleBGColor = array.getColor(R.styleable.BannerLayoutStyle_titleBGColor, titleBGColor);
        titleColor = array.getColor(R.styleable.BannerLayoutStyle_titleColor, titleColor);

        int shape = array.getInt(R.styleable.BannerLayoutStyle_indicatorShape, Shape.oval.ordinal());

        for (Shape shape1 : Shape.values()) {
            if (shape1.ordinal() == shape) {
                indicatorShape = shape1;
                break;
            }
        }

        selectedIndecatorWidth = (int) array.getDimension(R.styleable.BannerLayoutStyle_selectedIndicatorWidth, selectedIndecatorWidth);
        selectedIndicatorHeight = (int) array.getDimension(R.styleable.BannerLayoutStyle_selectedIndicatorHeight, selectedIndicatorHeight);

        unSelectedIndecatorWidth = (int) array.getDimension(R.styleable.BannerLayoutStyle_unSelectedIndicatorWidth, unSelectedIndecatorWidth);
        unSelectedIndicatorHeight = (int) array.getDimension(R.styleable.BannerLayoutStyle_unSelectedIndicatorHeight, unSelectedIndicatorHeight);


        indicatorSpace = (int) array.getDimension(R.styleable.BannerLayoutStyle_indicatorSpace, indicatorSpace);
        indicatorMargin = (int) array.getDimension(R.styleable.BannerLayoutStyle_indicatorMargin, indicatorMargin);

        autoPlayDuration = array.getInt(R.styleable.BannerLayoutStyle_autoPlayDuration, autoPlayDuration);
        scrollDuration = array.getInt(R.styleable.BannerLayoutStyle_scrollDuration, scrollDuration);

        isAutoPlay = array.getBoolean(R.styleable.BannerLayoutStyle_isAutoPlay, isAutoPlay);

        defaultImage = array.getResourceId(R.styleable.BannerLayoutStyle_defaultImage, defaultImage);

        array.recycle();

        LayerDrawable unSelectedLayerDrawable;
        LayerDrawable selectedLayerDrawable;

        GradientDrawable unSelectedGradientDrawable = new GradientDrawable();
        GradientDrawable selectedGradientDtawbale = new GradientDrawable();

        switch (indicatorShape) {
            case rect:
                unSelectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                selectedGradientDtawbale.setShape(GradientDrawable.RECTANGLE);
                break;
            case oval:
                unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
                selectedGradientDtawbale.setShape(GradientDrawable.OVAL);
                break;
        }

        unSelectedGradientDrawable.setColor(unSelectedIndicatorColor);
        unSelectedGradientDrawable.setSize(unSelectedIndecatorWidth, unSelectedIndicatorHeight);
        unSelectedLayerDrawable = new LayerDrawable(new Drawable[]{unSelectedGradientDrawable});
        unSelectedDrawable = unSelectedLayerDrawable;

        selectedGradientDtawbale.setColor(selectedIndicatorColor);
        selectedGradientDtawbale.setSize(selectedIndecatorWidth, selectedIndicatorHeight);
        selectedLayerDrawable = new LayerDrawable(new Drawable[]{selectedGradientDtawbale});

        selectedDrawable = selectedLayerDrawable;

    }

    /**
     * 添加本地图片
     *
     * @param viewRes 图片id集合
     * @param titles  标题集合，可空
     */
    public void setViewRes(List<Integer> viewRes, List<String> titles) {
        List<View> views = new ArrayList<>();
        itemCount = viewRes.size();
        if (titles != null && titles.size() != viewRes.size()) {
            throw new IllegalStateException("views.size() != titles.size()");
        }
        // 把数量拼凑到三个以上
        if (itemCount < 1) {
            throw new IllegalStateException("item count not equal zero");
        } else if (itemCount < 2) {
            views.add(getFrameLayoutView(viewRes.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(viewRes.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(viewRes.get(0), titles != null ? titles.get(0) : null, 0));

        } else if (itemCount < 3) {
            views.add(getFrameLayoutView(viewRes.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(viewRes.get(1), titles != null ? titles.get(1) : null, 1));
            views.add(getFrameLayoutView(viewRes.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(viewRes.get(1), titles != null ? titles.get(1) : null, 1));
        } else {
            for (int i = 0; i < viewRes.size(); i++) {
                views.add(getFrameLayoutView(viewRes.get(i), titles != null ? titles.get(i) : null, i));
            }
        }
        setViews(views);
    }

    //添加网络图片路径
    public void setViewUrls(List<String> urls, List<String> titles) {
        List<View> views = new ArrayList<>();
        itemCount = urls.size();
        if (titles != null && titles.size() != itemCount) {
            throw new IllegalStateException("views.size() != titles.size()");
        }

        //主要是解决当item为小于3个的时候滑动有问题，这里将其拼凑成3个以上
        if (itemCount < 1) {//当item个数0
            throw new IllegalStateException("item count not equal zero");
        } else if (itemCount < 2) { //当item个数为1
            views.add(getFrameLayoutView(urls.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(urls.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(urls.get(0), titles != null ? titles.get(0) : null, 0));
        } else if (itemCount < 3) {//当item个数为2
            views.add(getFrameLayoutView(urls.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(urls.get(1), titles != null ? titles.get(1) : null, 1));
            views.add(getFrameLayoutView(urls.get(0), titles != null ? titles.get(0) : null, 0));
            views.add(getFrameLayoutView(urls.get(1), titles != null ? titles.get(1) : null, 1));

        } else {
            for (int i = 0; i < urls.size(); i++) {
                views.add(getFrameLayoutView(urls.get(i), titles != null ? titles.get(i) : null, i));
            }
        }
        setViews(views);
    }

    private boolean isIndicator = false; //指示器开关
    private void setViews(final List<View> views) {

        mViewPager = new ViewPager(getContext());

        addView(mViewPager);
        setSliderTransformDuration(scrollDuration);
        if (isIndicator){
            //初始化indicatorContainer
            indicatorContainer = new LinearLayout(getContext());
            indicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            // 设置 指示点的位置
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            //设置margin
            params.setMargins(indicatorMargin, indicatorMargin, indicatorMargin, indicatorMargin);
            //添加指示器容器布局到SliderLayout
            addView(indicatorContainer, params);

            //初始化指示器，并添加到指示器容器布局
            for (int i = 0; i < itemCount; i++) {
                ImageView indicator = new ImageView(getContext());
                indicator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                indicator.setPadding(indicatorSpace, indicatorSpace, indicatorSpace, indicatorSpace);
                indicator.setImageDrawable(unSelectedDrawable);
                indicatorContainer.addView(indicator);
            }
        }

        LoopPagerAdapter pagerAdapter = new LoopPagerAdapter(views);
        mViewPager.setAdapter(pagerAdapter);
        //设置当前item到Integer.MAX_VALUE中间的一个值，看起来像无论是往前滑还是往后滑都是ok的
        //如果不设置，用户往左边滑动的时候已经划不动了
        int targetItemPosition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % itemCount;
        mViewPager.setCurrentItem(targetItemPosition);
        switchIndicator(targetItemPosition % itemCount);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switchIndicator(position % itemCount);
            }
        });
        startAutoPlay();

    }

    /**
     * 开始自动轮播
     */
    public void startAutoPlay() {
        stopAutoPlay(); // 避免重复消息
        if (isAutoPlay) {
            mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            startAutoPlay();
        } else {
            stopAutoPlay();
        }
    }


    /**
     * 停止自动轮播
     */
    public void stopAutoPlay() {
        if (isAutoPlay) {
            mHandler.removeMessages(WHAT_AUTO_PLAY);
        }

    }

    private void setSliderTransformDuration(int scrollDuration) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(mViewPager.getContext(), null, scrollDuration);
            mScroller.set(mViewPager, fixedSpeedScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoPlay();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startAutoPlay();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 切换指示器状态
     *
     * @param currentPosition 当前位置
     */
    private void switchIndicator(int currentPosition) {
        for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
            ((ImageView) indicatorContainer.getChildAt(i)).setImageDrawable(i == currentPosition ? selectedDrawable : unSelectedDrawable);
        }
    }


    public void setOnBannerItemClickListener(OnBannerItemClickListener onBannerItemClickListener) {
        this.mOnBannerItemClickListener = onBannerItemClickListener;
    }

    @NonNull
    private FrameLayout getFrameLayoutView(String url, String title, final int position) {
        FrameLayout frameLayout = new FrameLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        ImageView imageView = new ImageView(getContext());
        frameLayout.addView(imageView);
        frameLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnBannerItemClickListener != null) {

                    mOnBannerItemClickListener.onItemClick(position);
                }
            }
        });
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (defaultImage != 0){
            RequestOptions options = new RequestOptions();
            options.centerCrop()
                    .placeholder(R.drawable.img_none)
                    .error(R.drawable.img_none)
                    .fallback(R.drawable.img_none);
           // LogUtils.e("tupian",url);
            Glide.with(getContext())
                    .load(url)
                    .apply(options)
                    .into(imageView);
          //  Glide.with(getContext()).load(url).placeholder(defaultImage).centerCrop().into(imageView);
        }else {
           // LogUtils.e("tupian",url);
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.centerCrop()
                    .placeholder(R.drawable.img_none)
                    .error(R.drawable.img_none)
                    .fallback(R.drawable.img_none);
            Glide.with(getContext())
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
        if (!TextUtils.isEmpty(title)) {
            TextView textView = new TextView(getContext());
            textView.setText(title);
            textView.setTextColor(titleColor);
            textView.setPadding(titlePadding, titlePadding, titlePadding, titlePadding);
            textView.setBackgroundColor(titleBGColor);
            textView.getPaint().setFakeBoldText(true);
            layoutParams.gravity = Gravity.BOTTOM;
            frameLayout.addView(textView, layoutParams);
        }
        return frameLayout;
    }

    @NonNull
    private FrameLayout getFrameLayoutView(Integer res, String title, final int position) {
        FrameLayout frameLayout = new FrameLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        ImageView imageView = new ImageView(getContext());
        frameLayout.addView(imageView);
        frameLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBannerItemClickListener != null) {
                    mOnBannerItemClickListener.onItemClick(position);
                }
            }
        });
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(getContext()).load(res).apply(options).into(imageView);
        if (!TextUtils.isEmpty(title)) {
            TextView textView = new TextView(getContext());
            textView.setText(title);
            textView.setTextColor(titleColor);
            textView.setPadding(titlePadding, titlePadding, titlePadding, titlePadding);
            textView.setBackgroundColor(titleBGColor);
            textView.getPaint().setFakeBoldText(true);
            layoutParams.gravity = Gravity.BOTTOM;
            frameLayout.addView(textView, layoutParams);
        }
        return frameLayout;
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int position);
    }

    public class LoopPagerAdapter extends PagerAdapter {
        private List<View> views;

        public LoopPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            //Integer.MAX_VALUE = 2147483647
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (views.size() > 0) {
                //position % view.size()是指虚拟的position会在[0，view.size()）之间循环
                View view = views.get(position % views.size());
                if (container.equals(view.getParent())) {
                    container.removeView(view);
                }
                container.addView(view);
                return view;
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }

    public class FixedSpeedScroller extends Scroller {

        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, int duration) {
            this(context, interpolator);
            mDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

}
