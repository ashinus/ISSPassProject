package com.example.isspass.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.isspass.R;
import com.example.isspass.utils.Util;
import com.example.isspass.model.Response;

import java.util.ArrayList;

/**
 * Created by Ashutosh Singh on 11/30/2017.
 * Custom Adapter for Displaying the TSSPass Data in the List View
 */

public class ISSPassListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Response> mResponseList = new ArrayList();

    public ISSPassListAdapter(Context context, ArrayList<Response> responseList) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        mResponseList = responseList;
    }


    @Override
    public int getCount() {
        return mResponseList.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ListViewHolder mViewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
            mViewHolder = new ListViewHolder(view);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ListViewHolder) view.getTag();
        }

        mViewHolder.time.setText(Util.ConvertMilliIntoDateTime(mResponseList.get(position).getRisetime()));
        mViewHolder.duration.setText(mResponseList.get(position).getDuration()+" seconds ");

        return view;

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return mResponseList.get(i);
    }


    private class ListViewHolder {
        TextView duration, time;

        public ListViewHolder(View item) {
            duration = (TextView) item.findViewById(R.id.duration);
            time = (TextView) item.findViewById(R.id.risetime);
        }
    }

}
