package com.example.newapistackexchange;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OverflowAdapter extends ArrayAdapter<Overflow> {

    private List<Overflow> mOverflows;
    private RecyclerView.ViewHolder viewHolder;
    private Context context;
    private LayoutInflater inflater;
    private String imageUrls;


    public OverflowAdapter(@NonNull Context context, @NonNull List<Overflow> overflows) {
        super(context, 0, overflows);

        mOverflows = overflows;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)  {
        View view = convertView;
        Overflow current = mOverflows.get(position);


        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView Ans_count = (TextView) view.findViewById(R.id.ans_count);
        Ans_count.setText(String.valueOf(current.getAns_count()));
        int Answer_count=Integer.valueOf(current.getAns_count());
        if(Answer_count!=0){
            view.setBackgroundColor(Color.parseColor("#008000"));
        }
        else
            view.setBackgroundColor(Color.parseColor("#FF0000"));

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(String.valueOf(current.getDisplay_name()));

        //TextView tvOffset = (TextView) view.findViewById(R.id.offset_text_view);
        //tvOffset.setText(current.getScore());

        TextView score = (TextView) view.findViewById(R.id.score);
        score.setText(String.valueOf(current.getScore()));

        TextView view_count = (TextView) view.findViewById(R.id.view_count);
        view_count.setText(String.valueOf(current.getView_count()));

        Date date_c = new Date(current.getC_date());
        Date date_l_a = new Date(current.getL_A_date());

        TextView datec = (TextView) view.findViewById(R.id.creation_date);
        datec.setText(formatDate(date_c));

        TextView datela = (TextView) view.findViewById(R.id.last_act_date);
        datela.setText(formatDate(date_l_a));

        /*imageUrls=String.valueOf(current.getImage_link());
        ImageView profile = (ImageView) view.findViewById(R.id.image_view);

        Picasso.with(context)
                .load(imageUrls)
                .centerCrop()
                .fit()
                .into(profile)
                .setLoggingEnabled(true);
        */

        return view;
    }
    private String formatDate(Date date ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM DD, yyyy");
        return dateFormat.format(date);
    }

}
