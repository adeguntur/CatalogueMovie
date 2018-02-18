package com.example.ade.cataloguemovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ade on 18/02/2018.
 */

public class FilmAdapter extends BaseAdapter {

    private ImageView poster;

    private ArrayList<FilmItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public FilmAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<FilmItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final FilmItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public FilmItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.film_item, null);
            holder.textViewTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.poster = (ImageView) convertView.findViewById(R.id.poster);
            holder.textViewOverview = (TextView) convertView.findViewById(R.id.txtOverview);
            holder.textViewReleaseDate = (TextView) convertView.findViewById(R.id.txtReleaseDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + mData.get(position).getPoster())
                .into(holder.poster);
        holder.textViewTitle.setText(mData.get(position).getTitle());
        holder.textViewOverview.setText(mData.get(position).getOverview());
        holder.textViewReleaseDate.setText(mData.get(position).getRelease_date());
        return convertView;
    }

    public static class ViewHolder {
        TextView textViewTitle;
        ImageView poster;
        TextView textViewOverview;
        TextView textViewReleaseDate;
    }
}
