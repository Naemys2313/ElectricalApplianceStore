package com.naemys.electricalappliancestore.request;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomJsonStringRequest extends StringRequest {
    private Map<String, String> params;

    public CustomJsonStringRequest(final Activity activity, String url, final Map<String, String> params, final int requestCode) {
        super(Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                    String message = jsonObject.getString("message");

                    Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    if(success == 1) {
                        Intent intent = new Intent();
                        switch (requestCode) {
                            case Unit.ADD_CODE_REQUEST:
                                params.put(Unit._ID, jsonObject.getString("insert_id"));
                                intent.putExtra(Unit.DATA_EXTRA, (HashMap<String, String>) params);

                                activity.setResult(Activity.RESULT_OK, intent);
                                activity.finishActivity(Unit.ADD_CODE_REQUEST);

                                break;

                            case Unit.UPDATE_CODE_REQUEST:
                                intent.putExtra(Unit.DATA_EXTRA, (HashMap<String, String>) params);

                                activity.setResult(Activity.RESULT_OK, intent);
                                activity.finishActivity(Unit.UPDATE_CODE_REQUEST);

                                break;

                            case Unit.DELETE_CODE_REQUEST:
                                activity.setResult(Activity.RESULT_OK);
                                activity.finishActivity(Unit.DELETE_CODE_REQUEST);

                                break;
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

        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
