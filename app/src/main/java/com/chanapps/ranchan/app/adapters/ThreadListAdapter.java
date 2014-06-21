package com.chanapps.ranchan.app.adapters;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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
    private static final int CONTENT_WITHIMAGE_ID = R.id.thread_list_item_content_withimage;
    private static final int CONTENT_NOIMAGE_ID = R.id.thread_list_item_content_noimage;
    private static final int CHATS_ID = R.id.thread_list_item_chats;
    private static final int DATE_ID = R.id.thread_list_item_date;
    private static final int THUMB_ID = R.id.thread_list_item_thumb;
    private static final int IMAGES_ID = R.id.thread_list_item_images;
    private static final int IMAGEICON_ID = R.id.thread_list_item_imageicon;
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

    private void setContentText(View view, int resourceId, ThreadContent.ThreadItem item) {
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

}
