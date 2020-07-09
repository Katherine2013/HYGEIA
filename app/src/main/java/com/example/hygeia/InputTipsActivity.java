package com.example.hygeia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.SearchView;
//import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.hygeia.adapter.InputTipsAdapter;
import com.example.hygeia.poiutil.ToastUtil;
import com.example.hygeia.poiutil.Constants;


import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class InputTipsActivity extends Activity implements SearchView.OnQueryTextListener, Inputtips.InputtipsListener, OnItemClickListener, View.OnClickListener {
    private SearchView mSearchView;// 输入搜索关键字
    private ImageView mBack;
    private ListView mInputListView;
    private List<Tip> mCurrentTipList;
    private InputTipsAdapter mIntipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tips);
        initSearchView();
        mInputListView = (ListView) findViewById(R.id.inputtip_list);
        mInputListView.setOnItemClickListener(this);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
    }

    private void initSearchView() {
        mSearchView = (SearchView) findViewById(R.id.keyWord);
        mSearchView.setOnQueryTextListener(this);
        //设置SearchView默认为展开显示
        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setSubmitButtonEnabled(false);
    }

    /**
     * 输入提示回调
     *
     * @param tipList
     * @param rCode
     */
    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == 1000) {// 正确返回
            mCurrentTipList = tipList;
            List<String> listString = new ArrayList<String>();
            for (int i = 0; i < tipList.size(); i++) {
                listString.add(tipList.get(i).getName());
            }
            mIntipAdapter = new InputTipsAdapter(
                    getApplicationContext(),
                    mCurrentTipList);
            mInputListView.setAdapter(mIntipAdapter);
            mIntipAdapter.notifyDataSetChanged();
        } else {
            ToastUtil.showerror(this, rCode);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mCurrentTipList != null) {
            Tip tip = (Tip) adapterView.getItemAtPosition(i);
            Intent intent = new Intent();
            intent.putExtra(Constants.EXTRA_TIP, tip);
            setResult(MapPoiSearch.RESULT_CODE_INPUTTIPS, intent);

            // TODO 点击poi
            TextView tv_place = (TextView) findViewById(R.id.tv_place);
//            tv_place.setText(MapPoiSearch.RESULT_CODE_INPUTTIPS);
            Toast.makeText(InputTipsActivity.this,"Your location is "+tip.getName(), Toast.LENGTH_SHORT).show();
            writeData(tip.getPoint());
            this.finish();
        }
    }

    private void writeData(LatLonPoint startpoint) {
        String fileName = "startpoint.txt";
        String writeStr = startpoint.getLatitude() + " " + startpoint.getLongitude();
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + fileName;
            Log.e("TestFile", "有SD！！！！");
        } else {
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + fileName;
            Log.e("TestFile", "没有SD！！！！");
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 写入
        String strContent = writeStr;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                Log.e("TestFile", "Create the file:" + filePath);
                file.getParentFile().mkdirs();
                boolean cre = file.createNewFile();
                if (!cre) {
                    Log.e("TestFile", "生成文件失败！！！！");
                };
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes()); //将字符串写入
            raf.close();
            Log.e("TestFile", "写入成功" );
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }

    }
    /**
     * 按下确认键触发，本例为键盘回车或搜索键
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_WORDS_NAME, query);
        setResult(MapPoiSearch.RESULT_CODE_KEYWORDS, intent);
        this.finish();
        return false;
    }

    /**
     * 输入字符变化时触发
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        if (!IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, Constants.DEFAULT_CITY);
            Inputtips inputTips = new Inputtips(InputTipsActivity.this.getApplicationContext(), inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        } else {
            if (mIntipAdapter != null && mCurrentTipList != null) {
                mCurrentTipList.clear();
                mIntipAdapter.notifyDataSetChanged();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            this.finish();
        }
    }

    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }
}
