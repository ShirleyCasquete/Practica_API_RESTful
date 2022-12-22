package com.example.practicaapirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView TxtLista = findViewById(R.id.txtlista);
        ArrayList<String> lstUsuarios = new ArrayList<String>();
        JSONObject JSONobjet = new JSONObject(result);
        JSONArray JSONlista = JSONobjet.getJSONArray("data");
        String Lista = "";
        for (int i=0; i<JSONlista.length();i++){
            JSONObject Usuario =JSONlista.getJSONObject(i);
            Lista = Lista + Usuario.getString("name") + "\n";
        }
        TxtLista.setText(Lista);
    }
}