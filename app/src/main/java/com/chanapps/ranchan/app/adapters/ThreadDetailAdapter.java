package com.chanapps.ranchan.app.adapters;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.*;
import com.android.volley.toolbox.NetworkImageView;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.application.VolleySingleton;
import com.chanapps.ranchan.app.models.Preferences;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadItem;
import com.chanapps.ranchan.app.views.ImageSizingWebView;

import java.util.*;

/**
 * Created by johnarleyburns on 14/06/14.
 */
public class ThreadDetailAdapter extends ArrayAdapter<ThreadItem> {

    private static final int ITEM_LAYOUT_ID = R.layout.thread_detail_item;
    private static final int SELF_ITEM_LAYOUT_ID = R.layout.thread_detail_item_self;
    //private static final int IMAGE_ITEM_LAYOUT_ID = R.layout.thread_detail_image_item;
    private static final int CONTENT_ID = R.id.thread_list_item_content;
    private static final int FOOTER_ID = R.id.thread_list_item_footer;
    private static final int IMAGE_ID = R.id.thread_list_item_image;

    private final Object mLock = new Object();
    //private ThreadDetailType mDetailType = ThreadDetailType.CHATS;
    private ThreadItemsFilter mFilter;
    private SharedPreferences prefs;
    public List<ThreadItem> mItemsArray; // base data
    public List<ThreadItem> mItems; // filtered data

    public ThreadDetailAdapter(Context context, List<ThreadItem> objects) {
        super(context, ITEM_LAYOUT_ID, objects);
        mItemsArray = objects;
        mItems = objects;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private boolean isSelfPost(ThreadItem item) {
        return ThreadContent.getPosted().contains(item.id);
    }

    private int itemResourceId(ThreadItem item) {
        //switch (mDetailType) {
        //    default:
        //    case CHATS:
                if (isSelfPost(item)) {
                    return SELF_ITEM_LAYOUT_ID;
                }
                else {
                    return ITEM_LAYOUT_ID;
                }
        //    case IMAGES:
        //        return IMAGE_ITEM_LAYOUT_ID;
        //}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ThreadItem item = getItem(position);

        View view;
        int resId = itemResourceId(item);
        if (convertView == null || convertView.getId() != resId) {
            view = View.inflate(getContext(), resId, null);
        } else {
            view = convertView;
        }

        //switch (mDetailType) {
        //    default:
        //    case CHATS:
                return getItemView(view, item);
        //    case IMAGES:
        //        return getImageItemView(view, item);
        //}
    }

    private String itemFooter(ThreadItem item) {
        boolean isSelfPost = isSelfPost(item);
        String preNick = isSelfPost ? prefs.getString(Preferences.PREF_NICKNAME.prefKey, null) : item.nickname;
        String nickname = preNick == null ? getContext().getString(R.string.anonymous) : preNick;
        StringBuilder builder = new StringBuilder();
        if (isSelfPost) {
            builder.append(nickname);
            builder.append(getContext().getString(R.string.tilde_separator));
        }
        builder.append(item.shortDate(getContext()));
        if (!isSelfPost) {
            builder.append(getContext().getString(R.string.tilde_separator));
            builder.append(nickname);
        }
        return builder.toString();
    }

    private View getItemView(View view, ThreadItem item) {
        ((TextView) view.findViewById(FOOTER_ID)).setText(itemFooter(item));
        NetworkImageView image = (NetworkImageView) view.findViewById(IMAGE_ID);
        String url = item.thumbUrl();
        setContentText(view, CONTENT_ID, item);
        smartSetNetworkImageView(url, image);
        setImageDialogClickListener(item, image);
        return view;
    }
    /*
    private View getImageItemView(View view, ThreadItem item) {
        TextView dateView = ((TextView) view.findViewById(FOOTER_ID));
        if (dateView != null) {
            dateView.setText(item.shortDate(getContext()));
        }
        NetworkImageView image = (NetworkImageView) view.findViewById(IMAGE_ID);
        String url = item.thumbUrl();
        smartSetNetworkImageView(url, image);
        setImageDialogClickListener(item, image);
        return view;
    }

    private void setBrowseImageClickListener(final String url, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browse(url);
            }
        });
    }
    */
    private void browse(final String url) {
        Activity activity = getContext() instanceof Activity ? (Activity) getContext() : null;
        if (activity != null) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    private void smartSetNetworkImageView(String url, NetworkImageView view) {
        if (url == null) {
            view.setVisibility(View.GONE);
            view.setDefaultImageResId(0);
            view.setErrorImageResId(0);
            view.setImageUrl(null, VolleySingleton.getInstance().getImageLoader());
        } else {
            view.setVisibility(View.VISIBLE);
            view.setDefaultImageResId(R.drawable.pre_content);
            view.setErrorImageResId(R.drawable.no_content);
            view.setImageUrl(url, VolleySingleton.getInstance().getImageLoader());
        }
    }

    private void setContentText(View view, int resourceId, ThreadItem item) {
        TextView tv = (TextView)view.findViewById(resourceId);
        tv.setText(item.content);
        tv.setVisibility(item.content == null ? View.GONE : View.VISIBLE);
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

    /*
    public void setDetailType(ThreadDetailType detailType) {
        mDetailType = detailType;
        switch (mDetailType) {
            case CHATS:
        }
    }
    */
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
            //switch (mDetailType) {
            ///    default:
            //    case CHATS:
                    synchronized (mLock) { // Notice the declaration above
                        newItems = allItems;
                    }
            //        break;
            //    case IMAGES:
            //        newItems = new ArrayList<ThreadItem>();
            //        for (ThreadItem item : allItems) {
            //            if (item.hasImage()) {
            //                newItems.add(item);
            //            }
            //        }
            //        break;
            //}
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

    private void setImageDialogClickListener(final ThreadItem item, final View view) {
        final String url = item.imageUrl();
        final int width = item.width;
        final int height = item.height;
        final String id = item.id;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayImageDialog(url, id, width, height);
            }
        });
    }

    private void displayImageDialog(final String url, final String id, final int width, final int height) {
        Activity activity = getContext() instanceof Activity ? (Activity) getContext() : null;
        if (activity != null) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.image_dialog);
            final ImageSizingWebView webView = (ImageSizingWebView) dialog.findViewById(R.id.web_view);
            webView.setImageSize(width, height);
            webView.loadUrl(url);
            final ImageView downloadButton = (ImageView)dialog.findViewById(R.id.download_button);
            setDownloadListener(url, id, downloadButton, new ImageDialogCallback() {
                public void dismiss() {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    private abstract class ImageDialogCallback {
        abstract public void dismiss();
    }

    private void setDownloadListener(final String url, final String id, final View button, final ImageDialogCallback callback) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "download " + url, Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(url);
                DownloadManager.Request r = new DownloadManager.Request(uri);
                // This put the download in the same Download dir the browser uses
                r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, id);
                // When downloading music and videos they will be listed in the player
                // (Seems to be available since Honeycomb only)
                r.allowScanningByMediaScanner();
                // Notify user when download is completed
                // (Seems to be available since Honeycomb only)
                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                // Start download
                DownloadManager dm = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                dm.enqueue(r);
                callback.dismiss();
            }
        });
    }

}