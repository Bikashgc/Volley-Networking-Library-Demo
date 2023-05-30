package com.learning.sendretrivedatausingvolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL ="https://jsonplaceholder.typicode.com/posts";

    private ListView listView;
    private ArrayList<SampleData> sampleDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        sampleDataList = new ArrayList<>();
        sampleDataList.add(new SampleData(1,"Title 1", "Body 1"));

        MyAdapter myAdapter = new MyAdapter(this, sampleDataList);
        listView.setAdapter(myAdapter);
    }

    public void onPostBtnClicked(View view) {
        getVolleyRequest();
    }

    public void onGetBtnClicked(View view) {
        getVolleyRequest();
    }

    // Get Response from API call using volley
    private void getVolleyRequest() {
        // 1. Create a string request
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                JSON_URL,
                response -> {
                    decodeJSONString(response);
                },
                error -> {
                    Log.e("Volley Error", error.getMessage());
                }
        );
        // 2. Add that string request to request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    // Post request to API using volley

    private void postVolleyRequest() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                        JSON_URL,
                response -> {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Log.e("VOLLEY POST", error.getMessage());
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("title", "My Title");
                params.put("body", "My Body");
                params.put("userId", "1");
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }





    private void decodeJSONString(String jsonString) {
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                SampleData sampleData = new SampleData(
                        jsonObject.getInt("id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("body")
                );
                sampleDataList.add(sampleData);
            }

        }catch (Error err) {
            Log.e("Decode JSON", err.getMessage());
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}