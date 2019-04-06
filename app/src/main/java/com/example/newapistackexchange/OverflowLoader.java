package com.example.newapistackexchange;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class OverflowLoader extends AsyncTaskLoader<List<Overflow>> {
    private String mUrl;

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public OverflowLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Overflow> loadInBackground() {
        if(mUrl == null) {
            return null;
        }

        return QueryUtils.fetchData(mUrl);
    }
}
