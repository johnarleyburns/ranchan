package com.chanapps.ranchan.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.adapters.ThreadDetailAdapter;
import com.chanapps.ranchan.app.models.ThreadDetailType;

/**
 * A fragment representing a single Thread detail screen.
 * This fragment is either contained in a {@link com.chanapps.ranchan.app.controllers.ThreadListActivity}
 * in two-pane mode (on tablets) or a {@link com.chanapps.ranchan.app.controllers.ThreadDetailActivity}
 * on handsets.
 */
public class ThreadDetailImageFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private ThreadDetailType detailType = ThreadDetailType.IMAGES;
    private ThreadDetailAdapter mAdapter;
    private GridView mGrid;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ThreadDetailImageFragment() {
    }

    public ThreadDetailImageFragment(ThreadDetailAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
        }
        //mAdapter.setDetailType(ThreadDetailType.IMAGES);
        asyncLoadThreadList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thread_detail_image, container, false);

        mGrid = (GridView)view.findViewById(R.id.grid);
        mGrid.setAdapter(mAdapter);

        /*
            if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.thread_detail)).setText(mItem.content);
        }
        */
        return view;
    }

    private void asyncLoadThreadList() {
        Context context = getActivity();
        if (context == null) {
            return;
        }
        filter("");
    }

    /* methods called from activity */
    public void onRefreshList() {
        Toast.makeText(getActivity(), "Refreshing " + detailType, Toast.LENGTH_SHORT).show();

    }

    public void filter(String newText) {
        mAdapter.getFilter().filter(newText);
    }

    public void clearFilter() {
        filter(null);
    }
}
