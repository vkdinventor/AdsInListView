package com.vikash.app.adsinlistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by vikash on 21/4/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Viewholder> {

    private ArrayList<String> data;
    private Context context;
    private static  final int ITEM_ADS = 0;
    private static final int ITEM_DATA = 1;

    public ListAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        // 1. Declare your Views here

        public TextView textView;
        public AdView adView;


        public Viewholder(View itemView,int Type) {
            super(itemView);

            // 2. Define your Views here
            if(Type == ITEM_DATA){
                textView = (TextView)itemView.findViewById(R.id.textView);
            }else {
                adView = (AdView)itemView.findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder()
                        .build();
                adView.loadAd(adRequest);

            }

        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == ITEM_DATA){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_item, parent, false);
        }else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_ads_item, parent, false);
        }
        return new Viewholder(itemView,viewType);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) == null ){
            Log.v("TAG", "Item is ad");
            return ITEM_ADS;
        }
        else
            return ITEM_DATA;
    }
}