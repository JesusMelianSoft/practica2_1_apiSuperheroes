package com.jesusmelian.practica2_1_apisuperheroes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.jesusmelian.practica2_1_apisuperheroes.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText search_box;
    TextView urlDisplay;
    TextView searchResults;
    public class GitHubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String gitHubSearchResolve = null;
            try {
                gitHubSearchResolve = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return gitHubSearchResolve;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("MAIN ACTIVITY", "onPostExecute: " +s);
            searchResults.setText(s);
            if (s != null && !s.equals("")){
                searchResults.setText(s);
            }
            super.onPostExecute(s);
        }
    }
    //Para importar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        //Siempre hay que devolver true para que se muestre el menú
        return true;
    }

    //obtener el item pulsado del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.search){
            search_box = (EditText) findViewById(R.id.search_box);
            NetworkUtils network = new NetworkUtils();

            URL githubUrl = NetworkUtils.buildUrl(search_box.getText().toString());
            urlDisplay.setText(githubUrl.toString());

            new GitHubQueryTask().execute(githubUrl);
            //Esto no se puede hacer enm el hilo principal por que sino peta
            /*try {
                String response = NetworkUtils.getResponseFromHttpUrl(githubUrl);
                Log.i("MAIN ACTIVITY", response);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }
        //Devuelve true para que se ejecute4
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlDisplay = (TextView) findViewById(R.id.url_display);
        searchResults = (TextView) findViewById(R.id.github_search_results);

        searchResults.setText("HIOLAAA");
    }
}