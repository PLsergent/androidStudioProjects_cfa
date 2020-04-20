package com.example.surprise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = findViewById(R.id.webView);
        final EditText searchBar = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String searchText = searchBar.getText().toString();

            List<Character> characters = new ArrayList<>();
            for (char c:searchText.toCharArray()) {
                characters.add(c);
            }
            StringBuilder output = new StringBuilder(searchText.length());
            while (characters.size() != 0){
                int randPicker = (int) (Math.random() * characters.size());
                output.append(characters.remove(randPicker));
            }

            webView.loadUrl("http://" + output.toString() + ".com");
            }
        });
    }
}
