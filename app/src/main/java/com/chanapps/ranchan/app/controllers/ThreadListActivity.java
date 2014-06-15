package com.chanapps.ranchan.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.views.ThreadDetailFragment;
import com.chanapps.ranchan.app.views.ThreadListFragment;


/**
 * An activity representing a list of Threads. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link com.chanapps.ranchan.app.controllers.ThreadDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link com.chanapps.ranchan.app.views.ThreadListFragment} and the item details
 * (if present) is a {@link com.chanapps.ranchan.app.views.ThreadDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link com.chanapps.ranchan.app.views.ThreadListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ThreadListActivity extends FragmentActivity
        implements ThreadListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        if (findViewById(R.id.thread_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ThreadListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.thread_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ThreadListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ThreadDetailFragment.ARG_ITEM_ID, id);
            ThreadDetailFragment fragment = new ThreadDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.thread_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ThreadDetailActivity.class);
            detailIntent.putExtra(ThreadDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
