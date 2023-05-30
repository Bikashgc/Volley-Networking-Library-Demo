package com.learning.sendretrivedatausingvolley;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter <SampleData> {
    private final Activity aCxt;
    private final ArrayList<SampleData> sampleDataList;

    public MyAdapter(Activity aCxt, ArrayList<SampleData> sampleDataList) {
        super(aCxt, R.layout.list_item, sampleDataList);
        this.aCxt = aCxt;
        this.sampleDataList = sampleDataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(aCxt);
        View view = inflater.inflate(R.layout.list_item, null, true);
        TextView idView = view.findViewById(R.id.idView);
        TextView titleView = view.findViewById(R.id.titleView);
        TextView bodyView = view.findViewById(R.id.titleView2);

        SampleData sampleData = sampleDataList.get(position);
        idView.setText(String.valueOf(sampleData.getId()));
        titleView.setText(sampleData.getTitle());
        bodyView.setText(sampleData.getBody());
        return  view;
    }
}
