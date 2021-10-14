package ca.bcit.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    private RecyclerAdapter _recyclerAdapter;
    private RecyclerView _recyclerView;
    private ArrayList<Developer> _contriList;
    private HashMap<String, String> repoCompanies;
    private RequestQueue _requestQueue;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        URL = intent.getStringExtra("URL");

        _recyclerView = findViewById(R.id.recycler_view);
        _recyclerView.setHasFixedSize(true);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        _recyclerView.setLayoutManager(lm);

        _contriList = new ArrayList<Developer>();

        _requestQueue = Volley.newRequestQueue(this);
        queueParseJSON();

        setTitle(URL);

    }

    public void queueParseJSON() {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String jsonStr = "{\"contributors\":" + response.toString() + "}";
                        Gson gson = new Gson();

                        Contributors contributors = gson.fromJson(jsonStr, Contributors.class);
                        _contriList = contributors.getContributors();

                        _recyclerAdapter = new RecyclerAdapter(DetailActivity.this, _contriList);
                        _recyclerView.setAdapter(_recyclerAdapter);
                    }


                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        _requestQueue.add(request);

    }


}

