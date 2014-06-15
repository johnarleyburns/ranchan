package com.chanapps.ranchan.app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.NetworkImageView;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.application.VolleySingleton;
import com.chanapps.ranchan.app.models.ThreadContent;

import java.util.List;

/**
 * Created by johnarleyburns on 14/06/14.
 */
public class ThreadListAdapter extends ArrayAdapter<ThreadContent.ThreadItem> {

    private static final int RESOURCE_ID = R.layout.thread_list_item;
    private static final int CONTENT_ID = R.id.thread_list_item_content;
    private static final int CHATS_ID = R.id.thread_list_item_chats;
    private static final int DATE_ID = R.id.thread_list_item_date;
    private static final int THUMB_ID = R.id.thread_list_item_thumb;
    private static final int ADULT_ID = R.id.thread_list_item_adult;

    public ThreadListAdapter(Context context, List<ThreadContent.ThreadItem> objects) {
        super(context, RESOURCE_ID, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ThreadContent.ThreadItem item = getItem(position);

        View view;
        if (convertView == null) {
            view = View.inflate(getContext(), RESOURCE_ID, null);
        } else {
            view = convertView;
        }

        ((TextView) view.findViewById(CONTENT_ID)).setText(item.content);
        ((TextView) view.findViewById(CHATS_ID)).setText(String.valueOf(item.chats));
        ((TextView) view.findViewById(DATE_ID)).setText(item.shortDate(getContext()));
        view.findViewById(ADULT_ID).setVisibility(item.adult ? View.VISIBLE : View.INVISIBLE);

        NetworkImageView thumb = (NetworkImageView) view.findViewById(THUMB_ID);
        thumb.setDefaultImageResId(R.drawable.pre_content);
        thumb.setErrorImageResId(R.drawable.no_content);
        String url = item.thumbUrl();
        if (url != null) {
            //Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
            thumb.setImageUrl(url, VolleySingleton.getInstance().getImageLoader());
        }
        else {
            thumb.setImageBitmap(null);
        }

        return view;
    }
}
