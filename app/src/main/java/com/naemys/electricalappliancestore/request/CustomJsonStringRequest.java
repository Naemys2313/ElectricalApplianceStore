package com.naemys.electricalappliancestore.request;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.naemys.electricalappliancestore.AllDataTable;
import com.naemys.electricalappliancestore.models.Goods;
import com.naemys.electricalappliancestore.models.Model;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomJsonStringRequest extends StringRequest {
    private Map<String, String> params;
    private Model model;

    public CustomJsonStringRequest(final Activity activity, String url, final Model model, final int requestCode) {
        super(Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                    String message = jsonObject.getString("message");

                    Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    HashMap<String, Model> modelHashMap = new HashMap<>();
                    modelHashMap.put(Unit.DATA_EXTRA, model);

                    if(success == 1) {
                        Intent intent = new Intent();
                        switch (requestCode) {
                            case Unit.ADD_CODE_REQUEST:
                                intent.putExtra(Unit._ID, jsonObject.getString("insert_id"));
                                intent.putExtra(Unit.DATA_EXTRA, modelHashMap);

                                Log.d("TAG", "onResponse: here");


                                activity.setResult(Activity.RESULT_OK, intent);
                                activity.finish();

                                break;

                            case Unit.UPDATE_CODE_REQUEST:
                                intent.putExtra(Unit.DATA_EXTRA, modelHashMap);
                                intent.putExtra(Unit.UPDATE_EXTRA, true);

                                activity.setResult(Activity.RESULT_OK, intent);
                                activity.finish();

                                break;

                            case Unit.DELETE_CODE_REQUEST:
                                intent.putExtra(Unit.UPDATE_EXTRA, false);
                                activity.setResult(Activity.RESULT_OK, intent);
                                activity.finish();

                                break;

                            default:
                                Log.d(CustomJsonStringRequest.class.getSimpleName(), "onResponse: " + requestCode);
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

        this.model = model;

        if(requestCode == Unit.ADD_CODE_REQUEST) {
            params = model.toMap(false);
        } else {
            params = model.toMap(true);
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
