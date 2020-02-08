package com.example.emelinda.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.emelinda.Classes.Library;
import com.example.emelinda.R;

import java.util.ArrayList;

public class LibrariesAdapter extends BaseAdapter {
    Context context;
    ArrayList<Library> array = new ArrayList<Library>();
    LayoutInflater inflater;


    public LibrariesAdapter(Context context, ArrayList<Library> array) {
        this.context = context;
        this.array = array;
        inflater = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.model_a, null);
        TextView hid = (TextView) view.findViewById(R.id.hid);
        TextView htitle = (TextView) view.findViewById(R.id.htitle);
        TextView htype = (TextView) view.findViewById(R.id.htype);
        ImageView himg = (ImageView) view.findViewById(R.id.himg);
        hid.setText(array.get(i).getId());
        htitle.setText(array.get(i).getName());
        htype.setText(array.get(i).getType().getDescription());
        himg.setImageResource(array.get(i).getImg());
        return view;
    }
}
