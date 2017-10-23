package com.heri.contohvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyPHPActivity extends AppCompatActivity {

    String url = "http://192.168.18.1/jasa.php";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_php);
        textView = (TextView) findViewById(R.id.Txttamp);

        final RequestQueue rq;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network nwork = new BasicNetwork(new HurlStack());
        rq = new RequestQueue(cache, nwork);
        rq.start();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
                rq.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error mbah");
                error.printStackTrace();
            }
        });
        rq.add(stringRequest);
    }
}
