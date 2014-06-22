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
import com.chanapps.ranchan.app.models.ThreadItem;
import com.eaio.stringsearch.StringSearch;
import com.eaio.stringsearch.BNDMCI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnarleyburns on 14/06/14.
 */
public class ThreadListAdapter extends ArrayAdapter<ThreadItem> {

    private static final int RESOURCE_ID = R.layout.thread_list_item;
    private static final int CONTENT_WITHIMAGE_ID = R.id.thread_list_item_content_withimage;
    private static final int CONTENT_NOIMAGE_ID = R.id.thread_list_item_content_noimage;
    private static final int CHATS_ID = R.id.thread_list_item_chats;
    private static final int DATE_ID = R.id.thread_list_item_date;
    private static final int THUMB_ID = R.id.thread_list_item_thumb;
    private static final int IMAGES_ID = R.id.thread_list_item_images;
    private static final int IMAGEICON_ID = R.id.thread_list_item_imageicon;
    private static final int ADULT_ID = R.id.thread_list_item_adult;

    private final Object mLock = new Object();
    private ThreadItemsFilter mFilter;
    public List<ThreadItem> mItemsArray; // base data
    public List<ThreadItem> mItems; // filtered data

    public ThreadListAdapter(Context context, List<ThreadItem> objects) {
        super(context, RESOURCE_ID, objects);
        mItemsArray = objects;
        mItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ThreadItem item = getItem(position);

        View view;
        if (convertView == null) {
            view = View.inflate(getContext(), RESOURCE_ID, null);
        } else {
            view = convertView;
        }

        ((TextView) view.findViewById(CHATS_ID)).setText(String.valueOf(item.chats));
        ((TextView) view.findViewById(IMAGES_ID)).setText(String.valueOf(item.images));
        ((TextView) view.findViewById(DATE_ID)).setText(item.shortDate(getContext()));
        view.findViewById(IMAGEICON_ID).setVisibility(item.adult ? View.INVISIBLE : View.VISIBLE);
        view.findViewById(ADULT_ID).setVisibility(item.adult ? View.VISIBLE : View.INVISIBLE);

        NetworkImageView thumb = (NetworkImageView) view.findViewById(THUMB_ID);
        String url = item.thumbUrl();
        if (url == null) {
            ((TextView) view.findViewById(CONTENT_WITHIMAGE_ID)).setText(null);
            setContentText(view, CONTENT_NOIMAGE_ID, item);
            thumb.setDefaultImageResId(0);
            thumb.setErrorImageResId(0);
            thumb.setImageUrl(null, VolleySingleton.getInstance().getImageLoader());
        }
        else {
            ((TextView) view.findViewById(CONTENT_NOIMAGE_ID)).setText(null);
            setContentText(view, CONTENT_WITHIMAGE_ID, item);
            thumb.setDefaultImageResId(R.drawable.pre_content);
            thumb.setErrorImageResId(R.drawable.no_content);
            thumb.setImageUrl(url, VolleySingleton.getInstance().getImageLoader());
        }

        return view;
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

    private class ThreadItemsFilter extends Filter {
        protected FilterResults performFiltering(CharSequence prefix) {
            // Initiate our results object
            FilterResults results = new FilterResults();
            // If the adapter array is empty, check the actual items array and use it
            if (mItems == null) {
                synchronized (mLock) { // Notice the declaration above
                    mItems = new ArrayList<ThreadItem>(mItemsArray);
                }
            }
            // No prefix is sent to filter by so we're going to send back the original array
            if (prefix == null || prefix.length() == 0) {
                synchronized (mLock) {
                    results.values = mItemsArray;
                    results.count = mItemsArray.size();
                }
            } else {
                // Compare lower case strings
                char[] searchChars = prefix.toString().toLowerCase().toCharArray();
                StringSearch searcher = new BNDMCI();
                // Local to here so we're not changing actual array
                final List<ThreadItem> items = mItems;
                final int count = items.size();
                final List<ThreadItem> newItems = new ArrayList<ThreadItem>(count);
                for (int i = 0; i < count; i++) {
                    final ThreadItem item = items.get(i);
                    // First match against the whole, non-splitted value
                    if (searcher.searchChars(item.content.toCharArray(), searchChars) >= 0) {
                        newItems.add(item);
                    } else {} /* This is option and taken from the source of ArrayAdapter
                            final String[] words = itemName.split(" ");
                            final int wordCount = words.length;
                            for (int k = 0; k < wordCount; k++) {
                                if (words[k].startsWith(prefixString)) {
                                    newItems.add(item);
                                    break;
                                }
                            }
                        } */
                }
                // Set and return
                results.values = newItems;
                results.count = newItems.size();
            }
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
