package com.example.hygeia.ui.maproute;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.example.hygeia.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * 步行路径规划 示例
 */
public class WalkRouteActivity extends Activity implements OnMapClickListener,
        OnMarkerClickListener, OnInfoWindowClickListener, InfoWindowAdapter, OnRouteSearchListener {
	private AMap aMap;
	private MapView mapView;
	private Context mContext;
	private RouteSearch mRouteSearch;
	private WalkRouteResult mWalkRouteResult;
	private LatLonPoint mStartPoint = new LatLonPoint(39.976014, 116.317799);//起点，39.942295 116.335891
	private LatLonPoint mEndPoint = new LatLonPoint(39.983456, 116.3154950);//终点，39.995576 116.481288
	private LatLonPoint mStartPoint_m ;//起点，39.942295 116.335891
	private LatLonPoint mEndPoint_m;//终点，39.995576 116.481288

	private final int ROUTE_TYPE_WALK = 3;
	
	private RelativeLayout mBottomLayout, mHeadLayout;
	private TextView mRotueTimeDes, mRouteDetailDes;
	private ProgressDialog progDialog = null;// 搜索时进度条
	private int taskcompletetime = 600;
	private int alph = 300;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.route_activity);
		
		mContext = this.getApplicationContext();
		mapView = (MapView) findViewById(R.id.route_map);
		mapView.onCreate(bundle);// 此方法必须重写
		getLine();
		init();
		setfromandtoMarker();
		searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);
	}
	private void getLine(){
		//TODO:get file
		String filePath = null;
		String fileName = "data.txt";
		boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (hasSDCard) {
			filePath = Environment.getExternalStorageDirectory().toString() + File.separator + fileName;
			Log.e("TestFile", "有SD！！！！");
		} else {
			filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + fileName;
			Log.e("TestFile", "没有SD！！！！");
		}
		File file = new File(filePath);
		String filecon = getFileContent(file);
		//展示读入的起始点经纬度
		//Toast.makeText(WalkRouteActivity.this,filecon,Toast.LENGTH_SHORT).show();;
	}
	private String getFileContent(File file) {
		String content = "";
		if (!file.isDirectory()) {  //检查此路径名的文件是否是一个目录(文件夹)
			if (file.getName().endsWith("txt")) {//文件格式为""文件
				try {
					InputStream instream = new FileInputStream(file);
					if (instream != null) {
						InputStreamReader inputreader
								= new InputStreamReader(instream, "UTF-8");
						BufferedReader buffreader = new BufferedReader(inputreader);
						String line = "";
						//分行读取
						int tot =0;
						while ((line = buffreader.readLine()) != null) {
							content += line + "\n";
							tot++;
							if(tot==1){
								setlocation_start(line);
							}else{
								if (tot == 2){
									setlocation_end(line);
								}else{
									break;
								}
							}
							Log.e("read------", line+'\n');
						}
						instream.close();//关闭输入流
					}
				} catch (java.io.FileNotFoundException e) {
					Log.d("TestFile", "The File doesn't not exist.");
				} catch (IOException e) {
					Log.d("TestFile", e.getMessage());
				}
			}
		}
		return content;
	}

	private void setlocation_start(String line) {
		String[] arr = line.split(" ");
		mStartPoint_m = new LatLonPoint(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]));
	}
	private void setlocation_end(String line) {
		String[] arr = line.split(" ");
		mEndPoint_m = new LatLonPoint(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]));
	}
	private void setfromandtoMarker() {
		aMap.addMarker(new MarkerOptions()
		.position(AMapUtil.convertToLatLng(mStartPoint_m))
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.start)));
		aMap.addMarker(new MarkerOptions()
		.position(AMapUtil.convertToLatLng(mEndPoint_m))
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.end)));
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();	
		}
		aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
		registerListener();
		mRouteSearch = new RouteSearch(this);
		mRouteSearch.setRouteSearchListener(this);
		mBottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
		mHeadLayout = (RelativeLayout) findViewById(R.id.routemap_header);
		mHeadLayout.setVisibility(View.GONE);
		mRotueTimeDes = (TextView) findViewById(R.id.firstline);
		mRouteDetailDes = (TextView) findViewById(R.id.secondline);

	}

	/**
	 * 注册监听
	 */
	private void registerListener() {
		aMap.setOnMapClickListener(WalkRouteActivity.this);
		aMap.setOnMarkerClickListener(WalkRouteActivity.this);
		aMap.setOnInfoWindowClickListener(WalkRouteActivity.this);
		aMap.setInfoWindowAdapter(WalkRouteActivity.this);
		
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 开始搜索路径规划方案
	 */
	public void searchRouteResult(int routeType, int mode) {
		if (mStartPoint_m == null) {
			ToastUtil.show(mContext, "定位中，稍后再试...");
			return;
		}
		if (mEndPoint_m == null) {
			ToastUtil.show(mContext, "终点未设置");
		}
		showProgressDialog();
		final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
				mStartPoint_m, mEndPoint_m);
		if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
			WalkRouteQuery query = new WalkRouteQuery(fromAndTo, mode);
			mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
		}
	}

	@Override
	public void onBusRouteSearched(BusRouteResult result, int errorCode) {
		
	}

	@Override
	public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
		
	}

	@Override
	public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
		dissmissProgressDialog();
		aMap.clear();// 清理地图上的所有覆盖物
		if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
			if (result != null && result.getPaths() != null) {
				if (result.getPaths().size() > 0) {
					mWalkRouteResult = result;
					final WalkPath walkPath = mWalkRouteResult.getPaths()
							.get(0);
					if(walkPath == null) {
						return;
					}
					WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(
							this, aMap, walkPath,
							mWalkRouteResult.getStartPos(),
							mWalkRouteResult.getTargetPos());
					walkRouteOverlay.removeFromMap();
					walkRouteOverlay.addToMap();
					walkRouteOverlay.zoomToSpan();
					mBottomLayout.setVisibility(View.VISIBLE);
					int dis = (int) walkPath.getDistance();
					int dur = (int) walkPath.getDuration();
					String mistime = getmisstime(dur);
					String des = AMapUtil.getFriendlyTime(dur)+"("+AMapUtil.getFriendlyLength(dis)+") "
							+"Estimated time: " + mistime;
					mRotueTimeDes.setText(des);
					mRouteDetailDes.setVisibility(View.GONE);
					mBottomLayout.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(mContext,
									WalkRouteDetailActivity.class);
							intent.putExtra("walk_path", walkPath);
							intent.putExtra("walk_result",
									mWalkRouteResult);
							startActivity(intent);
						}
					});
				} else if (result != null && result.getPaths() == null) {
					ToastUtil.show(mContext, R.string.no_result);
				}
			} else {
				ToastUtil.show(mContext, R.string.no_result);
			}
		} else {
			ToastUtil.showerror(this.getApplicationContext(), errorCode);
		}
	}

	private String getmisstime(int dur) {
		int second = dur;
		second = 2*second + taskcompletetime + alph;
		if (second > 3600) {
			int hour = second / 3600;
			int miniate = (second % 3600) / 60;
			return hour + "h" + miniate + "min";
		}
		if (second >= 60) {
			int miniate = second / 60;
			return miniate + "min";
		}
		return second + "s";
	}


	/**
	 * 显示进度框
	 */
	private void showProgressDialog() {
		if (progDialog == null) {
			progDialog = new ProgressDialog(this);
		}
		    progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		    progDialog.setIndeterminate(false);
		    progDialog.setCancelable(true);
		    progDialog.setMessage("正在搜索");
		    progDialog.show();
	    }

	/**
	 * 隐藏进度框
	 */
	private void dissmissProgressDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onRideRouteSearched(RideRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}

