package com.example.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RequestQueue fRequestQueue;
    private VolleyS volley;

    Integer numero = 0;
    String nombre = "Andres";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

//        mTextViewResult = findViewById(R.id.tv_result);
//        Button btnParse = findViewById(R.id.btn1);
//
//        mQueue = Volley.newRequestQueue(this);
//
//        btnParse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jsonParse();
//            }
//        });
    }

        public void agregar(View view){
            numero();
        }


    @Override
    public void onClick(View view) {
        final JSONObject data = new JSONObject();
        try {
            data.put("nombre", "Andres");
            data.put("numero", numero);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://ramiro174.com/api/enviar/numero";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "Se enviaron los datos", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse1: ", error.toString());
            }
        });
        fRequestQueue.add(jsonObjectRequest);

    }
    private void numero() {
        String url = "http://ramiro174.com/api/numero";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            // Success
            @Override
            public void onResponse(JSONObject response) { // response = JSON Object
                TextView text = findViewById(R.id.textView);
                try {
                    Integer num = response.getInt("numero");
                    numero += num;
                    Toast.makeText(MainActivity.this, numero.toString(), Toast.LENGTH_SHORT).show();
                    if (numero > 21) {
                        Toast.makeText(MainActivity.this, "¡Perdiste!", Toast.LENGTH_SHORT).show();
                        text.setText("0");
                        numero = 0;
                        return;
                    }
                    if (numero == 21) {
                        Toast.makeText(MainActivity.this, "¡Ganaste!", Toast.LENGTH_SHORT).show();
                    }
                    text.setText((numero.toString()));
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() { // Error
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        }
        );

        fRequestQueue.add(request);
    }

    public void obtener(View view) {

        Intent intent= new Intent (MainActivity.this, ObtenerNumero.class);
        startActivity(intent);
    }




}




