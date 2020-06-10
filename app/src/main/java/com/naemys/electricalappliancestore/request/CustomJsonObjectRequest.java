package com.naemys.electricalappliancestore.request;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.naemys.electricalappliancestore.models.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomJsonObjectRequest extends JsonObjectRequest {


    public CustomJsonObjectRequest(String url, final Model model, final List list,  final RecyclerView.Adapter adapter) {
        super(Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getInt("success") == 1) {
                        JSONArray jsonArray = response.getJSONArray(model.getTableName());


                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Map<String, String> m = toMap(jsonObject);

                            list.add(model.fromMap(m));


                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    public static Map<String, String> toMap(JSONObject jsonObject) throws org.json.JSONException {
        Map<String, String> m = new HashMap<>();

        Iterator<String> keys = jsonObject.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            String value = jsonObject.getString(key);
            m.put(key, value);
        }

        return m;
    }
}
