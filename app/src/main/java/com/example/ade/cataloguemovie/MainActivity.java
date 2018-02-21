package com.example.ade.cataloguemovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<FilmItems>> {

    static final String EXTRAS_FILM = "EXTRAS_FILM";
    ListView listView;
    FilmAdapter adapter;
    EditText editFilm;
    Button buttonCari;

    ArrayList<FilmItems> mList = new ArrayList<>();
    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String film = editFilm.getText().toString();

            if (TextUtils.isEmpty(film)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_FILM, film);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new FilmAdapter(this);
        adapter.notifyDataSetChanged();
        listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

        editFilm = findViewById(R.id.edit_film);
        buttonCari = findViewById(R.id.btn_cari);

        buttonCari.setOnClickListener(myListener);

        String film = editFilm.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_FILM, film);

        getLoaderManager().initLoader(0, bundle, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Log.d(TAG, "onItemClick: id : " + mList.get(pos).getId());
            }
        });
    }

    @Override
    public Loader<ArrayList<FilmItems>> onCreateLoader(int id, Bundle args) {
        String kumpulanFilm = "";
        if (args != null) {
            kumpulanFilm = args.getString(EXTRAS_FILM);
        }

        return new MyAsyncTaskLoader(this, kumpulanFilm);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FilmItems>> loader, ArrayList<FilmItems> data) {
        mList = data;
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FilmItems>> loader) {
        adapter.setData(null);
    }
}
