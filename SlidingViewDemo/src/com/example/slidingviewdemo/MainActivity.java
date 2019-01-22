package com.example.slidingviewdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.slidingviewdemo.adapter.AdvAdapter;
import com.example.slidingviewdemo.view.SlidingView;
import com.example.slidingviewdemo.view.SlidingView.OnViewShowListener;


public class MainActivity extends ActionBarActivity  implements OnClickListener{

	private SlidingView wrapper;
	private ViewPager advPager;
	private GridView gridView;
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private boolean isContinue = true;
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}
	};
	private AtomicInteger what = new AtomicInteger(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        wrapper=(SlidingView) findViewById(R.id.wrapper);
        wrapper.setOnViewShowListener(new OnViewShowListener() {
			
			@Override
			public void onViewShow(boolean isShow, int flag) {
				// TODO 自动生成的方法存根
				
			}
		});
        initLeftView();
        initViewPager();
        initGridView();
    }


    private void initGridView() {
		// TODO 自动生成的方法存根
    	gridView=(GridView) findViewById(R.id.gridView);
    	// 生成动态数组，并且转入数据
    			ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
    			// -----------------------------------------------------------------------------
    			HashMap<String, Object> map = new HashMap<String, Object>();
    			map.put("ItemImage", R.drawable.sign);// 添加图像资源的ID
    			map.put("ItemText", "签到");// 按序号做ItemText
    			lstImageItem.add(map);

    			HashMap<String, Object> map1 = new HashMap<String, Object>();
    			map1.put("ItemImage", R.drawable.pay);// 添加图像资源的ID
    			map1.put("ItemText", "支付");// 按序号做ItemText
    			lstImageItem.add(map1);

    			HashMap<String, Object> map2 = new HashMap<String, Object>();
    			map2.put("ItemImage", R.drawable.query);// 添加图像资源的ID
    			map2.put("ItemText", "余额查询");// 按序号做ItemText
    			lstImageItem.add(map2);

    			HashMap<String, Object> map3 = new HashMap<String, Object>();
    			map3.put("ItemImage", R.drawable.scan);// 添加图像资源的ID
    			map3.put("ItemText", "扫码付");// 按序号做ItemText
    			lstImageItem.add(map3);

    			HashMap<String, Object> map4 = new HashMap<String, Object>();
    			map4.put("ItemImage", R.drawable.refund);// 添加图像资源的ID
    			map4.put("ItemText", "退货");// 按序号做ItemText
    			lstImageItem.add(map4);

    			HashMap<String, Object> map5 = new HashMap<String, Object>();
    			map5.put("ItemImage", R.drawable.revoke);// 添加图像资源的ID
    			map5.put("ItemText", "撤销");// 按序号做ItemText
    			lstImageItem.add(map5);

    			HashMap<String, Object> map6 = new HashMap<String, Object>();
    			map6.put("ItemImage", R.drawable.detail);// 添加图像资源的ID
    			map6.put("ItemText", "交易查询");// 按序号做ItemText
    			lstImageItem.add(map6);

    			HashMap<String, Object> map7 = new HashMap<String, Object>();
    			map7.put("ItemImage", R.drawable.loademv);// 添加图像资源的ID
    			map7.put("ItemText", "参数下载");// 按序号做ItemText
    			lstImageItem.add(map7);
    			
    			HashMap<String, Object> map8 = new HashMap<String, Object>();
    			map8.put("ItemImage", R.drawable.printsum);// 添加图像资源的ID
    			map8.put("ItemText", "交易汇总");// 按序号做ItemText
    			lstImageItem.add(map8);
    			
    			HashMap<String, Object> map9 = new HashMap<String, Object>();
    			map9.put("ItemImage", R.drawable.printsettle);// 添加图像资源的ID
    			map9.put("ItemText", "结算单");// 按序号做ItemText
    			lstImageItem.add(map9);
    			
    			HashMap<String, Object> map10 = new HashMap<String, Object>();
    			map10.put("ItemImage", R.drawable.settle);// 添加图像资源的ID
    			map10.put("ItemText", "结算");// 按序号做ItemText
    			lstImageItem.add(map10);
    			
    			SimpleAdapter saImageItems = new SimpleAdapter(this, // 没什么解释
    					lstImageItem,// 数据来源
    					R.layout.night_item,// night_item的XML实现

    					// 动态数组与ImageItem对应的子项
    					new String[] { "ItemImage", "ItemText" },

    					// ImageItem的XML文件里面的一个ImageView,两个TextView ID
    					new int[] { R.id.ItemImage, R.id.ItemText });
    			// 添加并且显示
    			gridView.setAdapter(saImageItems);
	}


	private void initViewPager() {
		// TODO 自动生成的方法存根
    	advPager = (ViewPager) findViewById(R.id.adv_pager);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);

		List<View> advPics = new ArrayList<View>();

		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.ic_zhulou);
		advPics.add(img1);

		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.ic_sport);
		advPics.add(img2);

		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.ic_book);
		advPics.add(img3);

		ImageView img4 = new ImageView(this);
		img4.setBackgroundResource(R.drawable.ic_zhulou);
		advPics.add(img4);

		imageViews = new ImageView[advPics.size()];

		for (int i = 0; i < advPics.size(); i++) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(5, 5, 5, 5);
			imageViews[i] = imageView;
			if (i == 0) {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_focus);
			} else {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_blur);
			}
			group.addView(imageViews[i]);
		}

		advPager.setAdapter(new AdvAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		advPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}
			}

		}).start();
	}
    private void whatOption() {
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
	}


	private void initLeftView() {
		// TODO 自动生成的方法存根
    	RelativeLayout ydwlsz=(RelativeLayout) findViewById(R.id.ll_ydwlsz);
		ydwlsz.setOnClickListener(this);
		RelativeLayout wlansz=(RelativeLayout) findViewById(R.id.ll_wxsz);
		wlansz.setOnClickListener(this);
		RelativeLayout lysz=(RelativeLayout) findViewById(R.id.ll_lysz);
		lysz.setOnClickListener(this);
		RelativeLayout vpnsz=(RelativeLayout) findViewById(R.id.ll_vpnsz);
		vpnsz.setOnClickListener(this);
		RelativeLayout xtsjsz=(RelativeLayout) findViewById(R.id.ll_xtsjsz);
		xtsjsz.setOnClickListener(this);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.ll_ydwlsz:
			//Toast.makeText(MainActivity.this, "移动网络设置", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));
			break;
		case R.id.ll_wxsz:
			//Toast.makeText(MainActivity.this, "WLAN设置", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			break;
		case R.id.ll_lysz:
			//Toast.makeText(MainActivity.this, "蓝牙设置", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
		    intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
		    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    try{
		        startActivity(intent);
		    } catch(ActivityNotFoundException ex){
		        ex.printStackTrace();
		    }catch (Exception e){
		        e.printStackTrace();
		    }
			break;
		case R.id.ll_vpnsz:
			//Toast.makeText(MainActivity.this, "VPN设置", Toast.LENGTH_SHORT).show();
			Intent vpnIntent = new Intent();                
			vpnIntent.setAction("android.net.vpn.SETTINGS");
			startActivity(vpnIntent);
			break;
		case R.id.ll_xtsjsz:
			//Toast.makeText(MainActivity.this, "系统时间设置", Toast.LENGTH_SHORT).show();
			Intent intentDate = new Intent(Settings.ACTION_DATE_SETTINGS);
			startActivity(intentDate);
			break;
		default:
			break;
		}
	}
    private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			what.getAndSet(arg0);
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.banner_dian_focus);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
			}
		}

	}
}
