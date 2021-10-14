package ca.bcit.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        ArrayList<Developer> values = (ArrayList<Developer>) intent.getSerializableExtra("contributorList");

        TextView tv = findViewById(R.id.textView);
        String str = "";
        for(Developer dev : values){
            str += ("Dev name: " + dev.getUsername() + "\n");
            str += ("Dev pic: " + dev.getPictureUrl() + "\n");
        }

        tv.setText(str);
    }

}