package com.example.ade.cataloguemovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<FilmItems>>  {

    ListView listView ;
    FilmAdapter adapter;
    EditText editKota;
    Button buttonCari;

    static final String EXTRAS_FILM = "EXTRAS_FILM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new FilmAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(adapter);

        editKota = (EditText)findViewById(R.id.edit_kota);
        buttonCari = (Button)findViewById(R.id.btn_kota);

        buttonCari.setOnClickListener(myListener);

        String film = editKota.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_FILM,film);

        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<FilmItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanFilm = "";
        if (args != null){
            kumpulanFilm = args.getString(EXTRAS_FILM);
        }

        return new MyAsyncTaskLoader(this,kumpulanFilm);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FilmItems>> loader, ArrayList<FilmItems> data) {

        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FilmItems>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String film = editKota.getText().toString();

            if (TextUtils.isEmpty(film))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_FILM,film);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };
}
