package com.example.newapistackexchange;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StartOverflowActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Overflow>> {

    private static final int OVERFLOW_LOADER_ID = 1;
    ArrayList<Overflow> overflows = new ArrayList<>();
    OverflowAdapter mAdapter;
    android.widget.ListView ListView;
    TextView mEmptyState;

    Button msearch;
    ProgressBar mProgressBar;

    private static String REQUEST_API;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overflow_list);


        mEmptyState = (TextView) findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            REQUEST_API=extras.getString("API");
        }

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager  = getLoaderManager();
            loaderManager.initLoader(OVERFLOW_LOADER_ID, null, this);

        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyState.setText(R.string.no_internet_connection);
        }

        ListView = (ListView) findViewById(R.id.list);

        ListView.setEmptyView(mEmptyState);

        mAdapter = new OverflowAdapter(this, overflows);
        ListView.setAdapter(mAdapter);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = overflows.get(i).getQues_link();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


    }

    @Override
    public Loader<List<Overflow>> onCreateLoader(int i, Bundle bundle) {
        return new OverflowLoader(this, REQUEST_API);
    }

    @Override
    public void onLoadFinished(Loader<List<Overflow>> loader, List<Overflow> overflows) {
        mAdapter.clear();
        if(overflows == null) {
            return;
        }
        mAdapter.addAll(overflows);

        mProgressBar.setVisibility(View.GONE);
        mEmptyState.setText(R.string.no_question_found);

    }

    @Override
    public void onLoaderReset(Loader<List<Overflow>> loader) {
        mAdapter.clear();
    }
}
