package ca.bcit.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;

import org.json.JSONArray;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contributors> _contriList;
    private RequestQueue _requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _contriList = new ArrayList<Contributors>();
        _requestQueue = Volley.newRequestQueue(this);
    }

    public void onClickFindContributor(View v) {
        Spinner repos = findViewById(R.id.repo);

        // GET
        String repoName = repos.toString();
        String endPoint = "https://api.github.com/repos/twbs/" +repoName + "/contributors";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, endPoint, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String jsonStr = "{\"contributors\":" + response.toString() + "}";
                        Gson gson = new Gson();

                        Contributors contributors = gson.fromJson(jsonStr, Contributors.class);
                        _contriList = contributors.getContributors();

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        _requestQueue.add(request);

        Intent i = new Intent(this, DetailActivity.class);

        //Add the bundle to the intent
        i.putExtra("contributorList", _contriList);

        //Fire that second activity
        startActivity(i);
    }

}