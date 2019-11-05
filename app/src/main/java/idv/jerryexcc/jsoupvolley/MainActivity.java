package idv.jerryexcc.jsoupvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, "https://udn.com/news/index", new Response.Listener<String>() {
            @Override
            public void onResponse(String html) {
                //Response Listener 反饋成功後會看到整個網頁的HTML原始碼(String)
                Document doc = Jsoup.parse(html);//使用Jsoup將response回來的HTML轉成Document方便直接取節點
                Log.d("TAG", "body h2: " + doc.body().select("h2"));
                /*
                * 詳細節點擷取可參考網站
                * http://xxs4129.pixnet.net/blog/post/165417214-android使用jsoup抓取網頁資料
                * */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error Listener 反饋回來有錯誤的時候執行這個區塊
                Log.d("TAG", "err: " + error.toString());
            }
        });
        //加入到請求佇列
        requestQueue.add(request);
    }
}
