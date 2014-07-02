package com.chanapps.ranchan.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.EditText;
import android.widget.ListView;


import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.adapters.ThreadDetailAdapter;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadContentTestHarness;
import com.chanapps.ranchan.app.models.ThreadDetailType;
import com.chanapps.ranchan.app.models.ThreadItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Thread detail screen.
 * This fragment is either contained in a {@link com.chanapps.ranchan.app.controllers.ThreadListActivity}
 * in two-pane mode (on tablets) or a {@link com.chanapps.ranchan.app.controllers.ThreadDetailActivity}
 * on handsets.
 */
public class ThreadDetailFragment extends ListFragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final boolean TEST_MODE = true;

    /**
     * The dummy content this fragment is presenting.
     */
    private ThreadDetailType detailType = ThreadDetailType.CHATS;
    private ThreadDetailAdapter mAdapter;
    private View mAttachPostButton;
    private View mSendPostButton;
    private EditText mNewPostText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ThreadDetailFragment() {
    }

    public ThreadDetailFragment(ThreadDetailAdapter adapter) {
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
        if (mAdapter == null) {
            if (TEST_MODE) {
                ThreadContentTestHarness.loadDetail();
                List<ThreadItem> items = new ArrayList<ThreadItem>();
                items.addAll(ThreadContent.getDetailItems());
                mAdapter = new ThreadDetailAdapter(getActivity(), items);
            }
            else {
                throw new UnsupportedOperationException("Only test mode currently implemented");
            }
        }
        //mAdapter.setDetailType(ThreadDetailType.CHATS);
        asyncLoadThreadList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thread_detail, container, false);
        View footer = inflater.inflate(R.layout.fragment_thread_detail_footer, null);
        setupFooter(footer);

        ListView list = (ListView)view.findViewById(android.R.id.list);
        list.addFooterView(footer);
        setListAdapter(mAdapter);

        /*
            if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.thread_detail)).setText(mItem.content);
        }
        */

        return view;
    }

    private void setupFooter(View footer) {
        mAttachPostButton = footer.findViewById(R.id.new_post_attach);
        mSendPostButton = footer.findViewById(R.id.new_post_send);
        mNewPostText = (EditText)footer.findViewById(R.id.new_post_text);
        mAttachPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Attach Image", Toast.LENGTH_SHORT).show();
            }
        });
        mSendPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postText = mNewPostText == null ? null : mNewPostText.getText().toString().trim();
                if (postText == null || postText.length() == 0) {
                    Toast.makeText(getActivity(), "Enter text to post", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Posting '" + postText +  "'...", Toast.LENGTH_SHORT).show();
            }
        });
        mNewPostText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mAttachPostButton.setVisibility(View.GONE);
                    mSendPostButton.setVisibility(View.VISIBLE);
                }
                else {
                    mSendPostButton.setVisibility(View.GONE);
                    mAttachPostButton.setVisibility(View.VISIBLE);
                }
            }
        });
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
