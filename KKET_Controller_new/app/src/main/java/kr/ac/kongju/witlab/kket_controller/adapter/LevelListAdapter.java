package kr.ac.kongju.witlab.kket_controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import kr.ac.kongju.witlab.kket_controller.R;

public class LevelListAdapter extends BaseAdapter {
    private ArrayList<LevelListItem> levelListItems;
    private LayoutInflater mInflater;

    private boolean enabled = true;

    public LevelListAdapter(Context context, ArrayList<LevelListItem> levelListItems) {
        this.levelListItems = levelListItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return levelListItems.size();
    }

    @Override
    public LevelListItem getItem(int position) {
        return levelListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LevelListItemViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.level_list_item, parent, false);

            holder = new LevelListItemViewHolder();
            holder.rdb = convertView.findViewById(R.id.rdbLevelList);
            holder.tvControlValues = convertView.findViewById(R.id.tvControlValues);
            holder.tvInfo = convertView.findViewById(R.id.tvInfo);

            convertView.setTag(holder);
        } else {
            holder = (LevelListItemViewHolder) convertView.getTag();
        }

        // set item values through holder
        LevelListItem item = levelListItems.get(position);
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
        levelListItems.forEach((item) -> item.setChecked(false));
        levelListItems.get(position).setChecked(true);
        notifyDataSetChanged();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}