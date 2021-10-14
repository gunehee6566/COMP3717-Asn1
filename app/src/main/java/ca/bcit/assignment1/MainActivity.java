package ca.bcit.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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

        // Add keys and values (repo, company)
        repoCompanies.put(getString(R.string.repo_freeCodeCamp), getString(R.string._freecodecamp));
        repoCompanies.put(getString(R.string.repo_996_ICU), getString(R.string._996_icu));
        repoCompanies.put(getString(R.string.repo_Vue), getString(R.string._vue));
        repoCompanies.put(getString(R.string.repo_Free_Programming_Books), getString(R.string._free_programming_books));
        repoCompanies.put(getString(R.string.repo_React), getString(R.string._react));

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