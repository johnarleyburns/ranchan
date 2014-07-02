package com.chanapps.ranchan.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.adapters.ThreadDetailAdapter;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadContentTestHarness;
import com.chanapps.ranchan.app.models.ThreadItem;
import com.chanapps.ranchan.app.fragments.ThreadDetailFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a single Thread detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ThreadListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link com.chanapps.ranchan.app.fragments.ThreadDetailFragment}.
 */
public class ThreadDetailActivity extends FragmentActivity {

    private static final boolean TEST_MODE = true;

    //private ThreadDetailType mDetailType = ThreadDetailType.CHATS;
    private ThreadDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_detail);

        // Show the Up button in the action bar.
        /*
        SpinnerAdapter navigationAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                R.array.action_detail,
                android.R.layout.simple_spinner_dropdown_item);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(navigationAdapter, navigationListener);
        getActionBar().setTitle(null);
        */
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (TEST_MODE) {
            ThreadContentTestHarness.loadDetail();
            List<ThreadItem> items = new ArrayList<ThreadItem>();
            items.addAll(ThreadContent.getDetailItems());
            mAdapter = new ThreadDetailAdapter(this, items);
        }
        else {
            throw new UnsupportedOperationException("Only test mode currently implemented");
        }
        //mAdapter.setDetailType(ThreadDetailType.IMAGES);

        if (savedInstanceState == null) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.thread_detail_container);
            if (fragment == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.thread_detail_container, createDetailFragment())
                        .commit();
            }
        }
        /*
        if (savedInstanceState != null) {
            int savedIndex = savedInstanceState.getInt(PersistedSetting.SAVED_INDEX.toString());
            getActionBar().setSelectedNavigationItem(savedIndex);
        }
        */
    }

    private Fragment createDetailFragment() {
        Bundle arguments = new Bundle();
        arguments.putString(ThreadDetailFragment.ARG_ITEM_ID,
                getIntent().getStringExtra(ThreadDetailFragment.ARG_ITEM_ID));
        ThreadDetailFragment fragment = new ThreadDetailFragment(mAdapter);
        fragment.setArguments(arguments);
        return fragment;
    }
    /*
    private Fragment createDetailImageFragment() {
        Bundle arguments = new Bundle();
        arguments.putString(ThreadDetailImageFragment.ARG_ITEM_ID,
                getIntent().getStringExtra(ThreadDetailImageFragment.ARG_ITEM_ID));
        ThreadDetailImageFragment fragment = new ThreadDetailImageFragment(mAdapter);
        fragment.setArguments(arguments);
        return fragment;
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.thread_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem attach = menu.findItem(R.id.menu_attach);
        MenuItem download = menu.findItem(R.id.menu_download);
        switch (mDetailType) {
            default:
            case CHATS:
                attach.setVisible(true);
                download.setVisible(false);
                break;
            case IMAGES:
                attach.setVisible(false);
                download.setVisible(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, ThreadListActivity.class));
                return true;
            case R.id.menu_attach_image:
                Toast.makeText(this, "image", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_attach_video:
                Toast.makeText(this, "video", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_attach_audio:
                Toast.makeText(this, "audio", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_download:
                Toast.makeText(this, "download", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /*
    private static enum PersistedSetting {
        SAVED_INDEX
    }

    private ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {
        @Override
        public boolean onNavigationItemSelected(int itemPosition, long itemId) {
            mDetailType = ThreadDetailType.values()[itemPosition];
            invalidateOptionsMenu();
            switch (mDetailType) {
                default:
                case CHATS:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.thread_detail_container, createDetailFragment())
                            .commit();
                    break;
                case IMAGES:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.thread_detail_container, createDetailImageFragment())
                            .commit();
                    break;
            }
            return false;
        }
    };
    */
}
