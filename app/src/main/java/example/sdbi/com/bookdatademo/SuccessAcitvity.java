package example.sdbi.com.bookdatademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SuccessAcitvity extends AppCompatActivity {
    private String id;
    private String catalog;
    private TextView tvTitle;
    private String strData;
    private String strBookItemData;
    private List<BookItem> bookList=new ArrayList<>();
    private String strtitle;
    private String strtag;
    private String strimg;
    private String strreading;
    private String strbytime;
    private TextView tvAllData;
    private ListView lvBookItem;
    private MyAdapterItem myAdapterItem;
    //    private MyBookItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_literature);
        sendRequestWithOkhttp();
//        tvAllData = (TextView) findViewById(R.id.tv_alldata);
//        initData();
        myAdapterItem = new MyAdapterItem(this, R.layout.bookitem,bookList);
        lvBookItem = (ListView) findViewById(R.id.lv_chinaliterature_item);
        lvBookItem.setAdapter(myAdapterItem);
        /**
         * 获取传递过来的id和catalog
         */
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        catalog=intent.getStringExtra("catalog");
        /**
         * 将title设置传递catalog值
         */
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(catalog);

//initData();
//        adapter = new MyBookItemAdapter(this,bookList);


//        adapter.notifyDataSetChanged();




    }
    private void sendRequestWithOkhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
//                    http://apis.juhe.cn/goodbook/query?key=你申请的key& catalog_id=246& rn=10& rn=10
                    Request request = new Request.Builder().url("http://apis.juhe.cn/goodbook/query?key=471882bc7360d3e0de1b6acbcf4dddf7 & catalog_id=" +id +"& rn=10& rn=10").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJsonWithJsonObject(responseData);
                    Log.d("ChinaMainActivity", responseData);
                    Log.d("ChinaMainActivityData", strData);
                    parseJsonWithJsonObject3(strData);
                    parseJsonWithJsonObject2(strBookItemData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJsonWithJsonObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData.toString());
            String resultcode = jsonObject.getString("resultcode");
            String reason = jsonObject.getString("reason");
            String result = jsonObject.getString("result");
            Log.d("ChinaMainActivity", resultcode + reason);
            Log.d("ChinaMainActivity", result);
            strData = result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithJsonObject2(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String img = jsonObject.getString("img");
                String tags = jsonObject.getString("catalog");
                String reading = jsonObject.getString("reading");
                String bytime = jsonObject.getString("bytime");
                BookItem item=new BookItem(title,tags,img,reading,bytime);
                bookList.add(item);
                strtitle=title;
                strimg=img;
                strtag=tags;
                strreading=reading;
                strbytime=bytime;
//                tvAllData.setText(title);
                Log.d("ChinaMainActivity","title="+title);
                Log.d("ChinaMainActivity","tags="+tags);
                Log.d("ChinaMainActivity","img="+img);
                Log.d("ChinaMainActivity","reading="+reading);
                Log.d("ChinaMainActivity","bytime="+bytime);


            }
//            BookItem item=new BookItem(strtitle,strtag,strimg,strreading,strbytime);
//            bookList.add(item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parseJsonWithJsonObject3(String jsonDataitem) {
        try {
            JSONObject jsonObject = new JSONObject(jsonDataitem.toString());
            String data = jsonObject.getString("data");
            Log.d("ChinaMainActivity------", data);
            strBookItemData=data;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void initData(){
        BookItem item = new BookItem("123","1231", "http://apis.juhe.cn/goodbook/img/c21d6b9af21587ad1bc5c50aafb1392a.jpg",  "123", "123");
        bookList.add(item);
    }
}
