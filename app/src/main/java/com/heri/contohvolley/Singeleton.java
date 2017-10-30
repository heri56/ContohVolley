package com.heri.contohvolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by heri on 10/23/17.
 */

public class Singeleton {
    private static Singeleton singeleton;
    private RequestQueue requestQueue;
    private static Context context;

    public Singeleton(Context ccontext) {
        context = ccontext;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Singeleton geInstance(Context context){
        if (singeleton == null){
            singeleton = new Singeleton(context);
        }
        return singeleton;
    }

    public<T> void addRequest(Request<T> request){
        requestQueue.add(request);
    }
}
