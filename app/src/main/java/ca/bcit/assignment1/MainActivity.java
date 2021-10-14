package ca.bcit.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Developer> _contriList;
    private RequestQueue _requestQueue;
    private HashMap<String, String> repoCompanies;
    private Button selectBtn;
    private Spinner repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _contriList = new ArrayList<Developer>();
        _requestQueue = Volley.newRequestQueue(this);

        repoCompanies = new HashMap<String, String>();

        // Add keys and values (Country, City)
        repoCompanies.put("freeCodeCamp", "freeCodeCamp");
        repoCompanies.put("996.ICU", "996icu");
        repoCompanies.put("Vue", "vuejs");
        repoCompanies.put("Free Progamming Books", "EbookFoundation");
        repoCompanies.put("React", "facebook");

        repos = findViewById(R.id.repo);
        selectBtn = findViewById(R.id.btnSubmit);
        Intent i = new Intent(this, DetailActivity.class);

        selectBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String currRepo = repos.getSelectedItem().toString();
                String currRepoCompany = repoCompanies.get(currRepo);

                String endPoint = "https://api.github.com/repos/"+ currRepoCompany + "/" +currRepo + "/contributors";
                i.putExtra("URL",endPoint);
                startActivity(i);

            }
        });
    }


}