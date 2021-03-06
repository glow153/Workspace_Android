package kr.ac.kongju.witlab.dailycontrol_multi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import kr.ac.kongju.witlab.dailycontrol_multi.R;

public class LevelListAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItems;
    private LayoutInflater mInflater;

    private boolean enabled = true;

    public LevelListAdapter(Context context, ArrayList<ListViewItem> listViewItems) {
        this.listViewItems = listViewItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public ListViewItem getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.level_list_item, parent, false);

            holder = new ListItemViewHolder();
            holder.rdb = convertView.findViewById(R.id.rdbLevelList);
            holder.tvControlValues = convertView.findViewById(R.id.tvControlValues);
            holder.tvInfo = convertView.findViewById(R.id.tvInfo);

            convertView.setTag(holder);
        } else {
            holder = (ListItemViewHolder) convertView.getTag();
        }

        // set item values through holder
        ListViewItem item = listViewItems.get(position);
        holder.rdb.setChecked(item.isChecked());
        holder.tvControlValues.setText(item.getTitle());
        holder.tvInfo.setText(item.getInfo());

        holder.rdb.setEnabled(enabled);
        holder.tvControlValues.setEnabled(enabled);
        holder.tvInfo.setEnabled(enabled);

        parent.setClickable(enabled);
        parent.setFocusable(enabled);

        return convertView;
    }

    public void checkOneItem(int position) {
        listViewItems.forEach((item) -> item.setChecked(false));
        listViewItems.get(position).setChecked(true);
        notifyDataSetChanged();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}