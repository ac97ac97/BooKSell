package example.sdbi.com.bookdatademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String resultData;
    private ListView lvBookDataCatalog;
    private MyAdatpter adatpter;
    private List<BooklDatalog> datalogs = new ArrayList<>();
    private String strid;
    private String strcatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequestWithOkhttp();
        adatpter = new MyAdatpter(this, R.layout.list_item, datalogs);
        lvBookDataCatalog = (ListView) findViewById(R.id.lv_book_catalog);
        lvBookDataCatalog.setAdapter(adatpter);
        lvBookDataCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BooklDatalog booklDatalog=datalogs.get(i);
                Toast.makeText(MainActivity.this,booklDatalog.getId()+i+"",Toast.LENGTH_SHORT).show();
                switch (i){
                    case 0:
                        Intent intent=new Intent(MainActivity.this,ChineseLiteratureActivity.class);
                        intent.putExtra("id",booklDatalog.getId());
                        intent.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(MainActivity.this,PersionActivity.class);
                        intent1.putExtra("id",booklDatalog.getId());
                        intent1.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(MainActivity.this,ChildActivity.class);
                        intent2.putExtra("id",booklDatalog.getId());
                        intent2.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(MainActivity.this,HistoryActivity.class);
                        intent3.putExtra("id",booklDatalog.getId());
                        intent3.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent(MainActivity.this,PhilosophyActivity.class);
                        intent4.putExtra("id",booklDatalog.getId());
                        intent4.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5=new Intent(MainActivity.this,ForeignActivity.class);
                        intent5.putExtra("id",booklDatalog.getId());
                        intent5.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6=new Intent(MainActivity.this,NovelActivity.class);
                        intent6.putExtra("id",booklDatalog.getId());
                        intent6.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7=new Intent(MainActivity.this,SoulTownAcitivity.class);
                        intent7.putExtra("id",booklDatalog.getId());
                        intent7.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8=new Intent(MainActivity.this,PhychologyActivity.class);
                        intent8.putExtra("id",booklDatalog.getId());
                        intent8.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9=new Intent(MainActivity.this,SuccessAcitvity.class);
                        intent9.putExtra("id",booklDatalog.getId());
                        intent9.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10=new Intent(MainActivity.this,EducateActivity.class);
                        intent10.putExtra("id",booklDatalog.getId());
                        intent10.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11=new Intent(MainActivity.this,EssayActivity.class);
                        intent11.putExtra("id",booklDatalog.getId());
                        intent11.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent11);
                        break;
                    case 12:
                        Intent intent12=new Intent(MainActivity.this,FinancialActivity.class);
                        intent12.putExtra("id",booklDatalog.getId());
                        intent12.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent12);
                        break;
                    case 13:
                        Intent intent13=new Intent(MainActivity.this,ManagerActivity.class);
                        intent13.putExtra("id",booklDatalog.getId());
                        intent13.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent13);
                        break;
                    case 14:
                        Intent intent14=new Intent(MainActivity.this,ClassicActivity.class);
                        intent14.putExtra("id",booklDatalog.getId());
                        intent14.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent14);
                        break;
                    case 15:
                        Intent intent15=new Intent(MainActivity.this,EconomicalActivity.class);
                        intent15.putExtra("id",booklDatalog.getId());
                        intent15.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent15);
                        break;
                    case 16:
                        Intent intent16=new Intent(MainActivity.this,ComputerActivity.class);
                        intent16.putExtra("id",booklDatalog.getId());
                        intent16.putExtra("catalog",booklDatalog.getCatalog());
                        startActivity(intent16);
                        break;


                }

            }
        });


    }

    private void sendRequestWithOkhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://apis.juhe.cn/goodbook/catalog?key=471882bc7360d3e0de1b6acbcf4dddf7 & dtype=json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJsonWithJsonObject(responseData);
                    Log.d("MainActivity", responseData);
                    Log.d("MainActivity", "------------" + resultData);
                    parseJsonWithJsonObject2(resultData);
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
            Log.d("MainActivity", resultcode + reason);
            Log.d("MainActivity", result);
            resultData = result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithJsonObject2(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String catalog = jsonObject.getString("catalog");
                Log.d("MainActivity", id + catalog);
                strid = id;
                strcatalog = catalog;
                BooklDatalog datalog = new BooklDatalog(id, catalog);
                datalogs.add(datalog);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adatpter.notifyDataSetChanged();
                    }
                });

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
