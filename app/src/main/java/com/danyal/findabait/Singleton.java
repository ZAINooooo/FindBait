package com.danyal.findabait;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private static Singleton mInstance;
    private RequestQueue mRequest;
    private static Context mCtx;

    private Singleton(Context context) {
        mCtx = context;
        mRequest = getRequestQue();
    }

    public RequestQueue getRequestQue() {
        if (mRequest == null) {
            mRequest = Volley.newRequestQueue(mCtx);
        }
        return mRequest;
    }

    public static synchronized Singleton getInstance(Context c) {
        if (mInstance == null) {
            mInstance = new Singleton(c);
        }
        return mInstance;
    }

    public <T> void addToRequestQue(Request<T> request) {
        mRequest.add(request);
    }
}