package com.example.design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ObtenerNumero extends AppCompatActivity {
    private RequestQueue fRequestQueue;
    private VolleyS volley;
    ArrayList<Usuario> listaUsuario;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtener_numero);
        volley = VolleyS.getInstance(this.getApplicationContext());

        fRequestQueue = volley.getRequestQueue();
        obtener();
    }

    public void obtener() {
        listaUsuario = new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String url = "http://ramiro174.com/api/obtener/numero";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    ArrayList<Usuario> listaUsuario;
                    Type talp = new TypeToken<ArrayList<Usuario>>() {
                    }.getType();
                    String p = response.getString("resultados");

                    listaUsuario = new Gson().fromJson(p, talp);
                    ResultadoUsuario adapter = new ResultadoUsuario(listaUsuario);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse1: ", error.toString());
            }
        });
        fRequestQueue.add(jsonObjectRequest);
    }
}
