package com.example.design;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {

    private static VolleyS mVolleyS = null;
    private RequestQueue mRequestQueue;

    private VolleyS(Context context){  //Constructor el contexto se pasa al requestqueue
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context){
        if(mVolleyS == null){
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }
    public RequestQueue getRequestQueue(){ return mRequestQueue;}
}
