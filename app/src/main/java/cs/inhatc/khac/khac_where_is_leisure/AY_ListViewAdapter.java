package cs.inhatc.khac.khac_where_is_leisure;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*Dvelop By KimAYoung*/
public class AY_ListViewAdapter extends BaseAdapter implements Filterable {

    private ArrayList<AY_ListViewItem> listViewItemAYList = new ArrayList<AY_ListViewItem>();
    private ArrayList<AY_ListViewItem> filteredItemList = listViewItemAYList;
    Filter listFilter;
    public AY_ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_ay_listview_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1);
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2);

        AY_ListViewItem AYListViewItem = filteredItemList.get(position);

        iconImageView.setImageDrawable(AYListViewItem.getIcon());
        titleTextView.setText(AYListViewItem.getTitle());
        descTextView.setText(AYListViewItem.getDesc());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    public void addItem(Drawable icon, String title, String desc) {
        AY_ListViewItem item = new AY_ListViewItem();
        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);
        listViewItemAYList.add(item);
    }

    @Override public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemAYList;
                results.count = listViewItemAYList.size() ;
            } else {
                ArrayList<AY_ListViewItem> itemList = new ArrayList<AY_ListViewItem>() ;

                for (AY_ListViewItem item : listViewItemAYList) {
                    if (item.getTitle().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.getDesc().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<AY_ListViewItem>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }
}











