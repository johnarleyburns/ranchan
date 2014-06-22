package com.chanapps.ranchan.app.views;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.adapters.ThreadListAdapter;
import com.chanapps.ranchan.app.models.ListType;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A list fragment representing a list of Threads. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link com.chanapps.ranchan.app.views.ThreadDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ThreadListFragment extends ListFragment {

    public static final String REFRESH_THREAD_LIST_ACTION = "com.chanapps.ranchan.app.ThreadListFragment.refresh";

    private static final boolean TEST_MODE = true;

    private ListType listType = ListType.HOME;
    private ThreadListAdapter mAdapter;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ThreadListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver, new IntentFilter(REFRESH_THREAD_LIST_ACTION));
        asyncLoadThreadList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_thread_listview, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(ThreadContent.ITEMS.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            asyncLoadThreadList();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    private void asyncLoadThreadList() {
        Context context = getActivity();
        if (context == null) {
            return;
        }
        if (TEST_MODE) {
            List<ThreadItem> items = new ArrayList<ThreadItem>();
            boolean adultEnabled = SettingsFragment.Preferences.adultEnabled(context);
            if (!adultEnabled) {
                for (ThreadItem item : ThreadContent.ITEMS) {
                    if (!item.adult) {
                        items.add(item);
                    }
                }
            }
            else {
                items.addAll(ThreadContent.ITEMS);
            }
            mAdapter = new ThreadListAdapter(context, items);
            setListAdapter(mAdapter);
        }
        else {
            throw new UnsupportedOperationException("Only test mode currently implemented");
        }
    }

    /* methods called from activity */
    public void onRefreshList() {
        Toast.makeText(getActivity(), "Refreshing " + listType, Toast.LENGTH_SHORT).show();

    }

    public void onChangeListType(ListType listType) {
        this.listType = listType;
        Toast.makeText(getActivity(), "Loading " + listType, Toast.LENGTH_SHORT).show();

    }

    public void filter(String newText) {
        mAdapter.getFilter().filter(newText);
    }

}
