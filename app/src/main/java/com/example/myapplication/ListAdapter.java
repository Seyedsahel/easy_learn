package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class ListAdapter extends BaseAdapter {

    ArrayList<lessonClass> lessonList;
    Context context;
    LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public ListAdapter(ArrayList<lessonClass> lessonList, Context context) {
        this.lessonList = lessonList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lessonList.size();
    }

    @Override
    public Object getItem(int position) {
        return lessonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_row, null);

        ImageView img = view.findViewById(R.id.listViewImage);
        if(lessonList.get(i).getStatus())
            img.setImageResource(R.drawable.check_circle);
        else
            img.setImageResource(R.drawable.circle);

        TextView title = (TextView) view.findViewById(R.id.listViewTitle);
        title.setText(lessonList.get(i).getTitle());

        TextView shortDescription = (TextView) view.findViewById(R.id.listViewDesc);
        shortDescription.setText(lessonList.get(i).getDes1());



        return view;
    }
}
