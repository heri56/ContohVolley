package com.heri.contohvolley.phpvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.heri.contohvolley.MainActivity;
import com.heri.contohvolley.R;
import com.heri.contohvolley.phpvolley.adapter.AdapterProduk;
import com.heri.contohvolley.phpvolley.model.Produk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PHPVolleyAndroidActivity extends AppCompatActivity {

    private static final  String urlpass = "http://192.168.18.1/PHPVolleyAndroid/index.php";

    List<Produk> prlist;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phpvolley_android);
        recyclerView = findViewById(R.id.Reycyle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prlist = new ArrayList<>();
        loadproduk();
    }
    private void loadproduk(){
        StringRequest srq = new StringRequest(Request.Method.GET, urlpass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject produk = array.getJSONObject(i);
                        prlist.add(new Produk(
                                produk.getInt("id"),
                                produk.getString("title"),
                                produk.getString("shortdesc"),
                                produk.getDouble("rating"),
                                produk.getDouble("price"),
                                produk.getString("image")));
                    }
                    AdapterProduk adapterProduk = new AdapterProduk(PHPVolleyAndroidActivity.this, prlist);
                    recyclerView.setAdapter(adapterProduk);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(srq);

    }
}
