package com.chanapps.ranchan.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.application.VolleySingleton;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadDetailType;
import com.chanapps.ranchan.app.models.ThreadItem;

import java.util.*;

/**
 * Created by johnarleyburns on 14/06/14.
 */
public class ThreadDetailAdapter extends ArrayAdapter<ThreadItem> {

    private static final int ITEM_LAYOUT_ID = R.layout.thread_detail_item;
    private static final int IMAGE_ITEM_LAYOUT_ID = R.layout.thread_detail_image_item;
    private static final int CONTENT_WITHIMAGE_ID = R.id.thread_list_item_content_withimage;
    private static final int CONTENT_NOIMAGE_ID = R.id.thread_list_item_content_noimage;
    private static final int DATE_ID = R.id.thread_list_item_date;
    private static final int THUMB_ID = R.id.thread_list_item_thumb;
    private static final int IMAGE_ID = R.id.thread_list_item_image;

    private final Object mLock = new Object();
    private ThreadDetailType mDetailType = ThreadDetailType.CHATS;
    private ThreadItemsFilter mFilter;
    public List<ThreadItem> mItemsArray; // base data
    public List<ThreadItem> mItems; // filtered data

    public ThreadDetailAdapter(Context context, List<ThreadItem> objects) {
        super(context, ITEM_LAYOUT_ID, objects);
        mItemsArray = objects;
        mItems = objects;
    }

    private int itemResourceId() {
        switch (mDetailType) {
            default:
            case CHATS:
                return ITEM_LAYOUT_ID;
            case IMAGES:
                return IMAGE_ITEM_LAYOUT_ID;
        }
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ThreadItem item = getItem(position);

        View view;
        int resId = itemResourceId();
        if (convertView == null || convertView.getId() != resId) {
            view = View.inflate(getContext(), resId, null);
        } else {
            view = convertView;
        }

        switch (mDetailType) {
            default:
            case CHATS:
                return getItemView(view, item);
            case IMAGES:
                return getImageItemView(view, item);
        }
    }
    
    private View getItemView(View view, ThreadItem item) {
        ((TextView) view.findViewById(DATE_ID)).setText(item.shortDate(getContext()));
        NetworkImageView thumb = (NetworkImageView) view.findViewById(THUMB_ID);
        String url = item.thumbUrl();
        int ignoreTextId;
        int textId;
        if (url == null) {
            ignoreTextId = CONTENT_WITHIMAGE_ID;
            textId = CONTENT_NOIMAGE_ID;
        }
        else {
            ignoreTextId = CONTENT_NOIMAGE_ID;
            textId = CONTENT_WITHIMAGE_ID;
        }
        ((TextView) view.findViewById(ignoreTextId)).setText(null);
        setContentText(view, textId, item);
        smartSetNetworkImageView(url, thumb);
        return view;
    }

    private View getImageItemView(View view, ThreadItem item) {
        TextView dateView = ((TextView) view.findViewById(DATE_ID));
        if (dateView != null) {
            dateView.setText(item.shortDate(getContext()));
        }
        NetworkImageView image = (NetworkImageView) view.findViewById(IMAGE_ID);
        //String url = item.previewUrl();
        String url = item.thumbUrl();
        smartSetNetworkImageView(url, image);
        return view;
    }

    private void smartSetNetworkImageView(String url, NetworkImageView view) {
        if (url == null) {
            view.setDefaultImageResId(0);
            view.setErrorImageResId(0);
            view.setImageUrl(null, VolleySingleton.getInstance().getImageLoader());
        }
        else {
            view.setDefaultImageResId(R.drawable.pre_content);
            view.setErrorImageResId(R.drawable.no_content);
            view.setImageUrl(url, VolleySingleton.getInstance().getImageLoader());
        }

    }
    
    private void setContentText(View view, int resourceId, ThreadItem item) {
        /*
        if (item.mine) {
            StringBuilder b = new StringBuilder();
            b.append("<b>");
            b.append(getContext().getString(R.string.your_thread));
            b.append("</b>");
            b.append(" ");
            b.append(item.content);
            ((TextView) view.findViewById(resourceId)).setText(Html.fromHtml(b.toString()));

        }
        else {
        */
            ((TextView) view.findViewById(resourceId)).setText(item.content);
        //}
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
    @Override
    public ThreadItem getItem(int position) {
        return mItems.get(position);
    }
    @Override
    public int getPosition(ThreadItem item) {
        return mItems.indexOf(item);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ThreadItemsFilter();
        }
        return mFilter;
    }

    public void setDetailType(ThreadDetailType detailType) {
        mDetailType = detailType;
        switch (mDetailType) {
            case CHATS:
        }
    }

    private class ThreadItemsFilter extends Filter {
        protected FilterResults performFiltering(CharSequence prefix) {
            // Initiate our results object
            // If the adapter array is empty, check the actual items array and use it
            if (mItems == null) {
                synchronized (mLock) { // Notice the declaration above
                    mItems = new ArrayList<ThreadItem>(mItemsArray);
                }
            }
            return filterByType();
        }

        protected FilterResults filterByType() {
            FilterResults results = new FilterResults();
            // Local to here so we're not changing actual array
            List<ThreadItem> allItems = ThreadContent.getDetailItems();
            List<ThreadItem> newItems;
            switch (mDetailType) {
                default:
                case CHATS:
                    synchronized (mLock) { // Notice the declaration above
                        newItems = allItems;
                    }
                    break;
                case IMAGES:
                    newItems = new ArrayList<ThreadItem>();
                    for (ThreadItem item : allItems) {
                        if (item.hasImage()) {
                            newItems.add(item);
                        }
                    }
                    break;
            }
            results.values = newItems;
            results.count = newItems.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence prefix, FilterResults results) {
            //noinspection unchecked
            mItems = (List<ThreadItem>) results.values;
            // Let the adapter know about the updated list
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
