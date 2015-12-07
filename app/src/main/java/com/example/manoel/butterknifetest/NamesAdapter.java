package com.example.manoel.butterknifetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by manoel on 07/12/15.
 */
public class NamesAdapter extends BaseAdapter {

    private final List<String> namesList;
    private final LayoutInflater inflater;

    public NamesAdapter(Context context, List<String> namesList) {
        this.namesList = namesList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return namesList.size();
    }

    @Override
    public Object getItem(int position) {
        return namesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.adapter_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.txvName.setText(namesList.get(position));

        return convertView;
    }

    // Another use is simplifying the view holder pattern inside of a list adapter.
    static class ViewHolder {
        @Bind(R.id.txvName)
        TextView txvName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
