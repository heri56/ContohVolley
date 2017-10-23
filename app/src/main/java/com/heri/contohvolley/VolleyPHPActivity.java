package com.heri.contohvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

        final RequestQueue rque= Volley.newRequestQueue(VolleyPHPActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                textView.setText(response);
                rque.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError responseerror) {

                textView.setText("Something wrong.....");
                responseerror.printStackTrace();
                rque.stop();
            }
        });
        rque.add(stringRequest);
    }
}
