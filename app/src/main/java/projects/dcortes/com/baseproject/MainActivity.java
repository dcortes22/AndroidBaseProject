package projects.dcortes.com.baseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Map;

import projects.dcortes.com.baseproject.service.ServiceHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceHandler handler = new ServiceHandler();
        Map<String, Object> params = new ArrayMap<String, Object>();
        params.put("title", "foo");
        params.put("body", "bar");
        params.put("userId", "1");
        handler.addHeader("Content-Type","Application/json");

        handler.objectRequest("http://jsonplaceholder.typicode.com/posts", Request.Method.POST,
                params, JSONObject.class, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.getMessage());
                    }
                });
    }
}
