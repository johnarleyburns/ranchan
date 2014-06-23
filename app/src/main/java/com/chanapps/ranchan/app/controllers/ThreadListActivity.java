package com.chanapps.ranchan.app.controllers;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.models.ListType;
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
        implements ThreadListFragment.Callbacks,
        ActionBar.TabListener
{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private boolean isSearchExpanded;
    private String mSearchQuery;
    private MenuItem mSearchItem;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_list);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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

        ActionBar.Tab tab1 = getActionBar().newTab();
        tab1.setTag(ListType.HOME);
        tab1.setText(ListType.HOME.stringId);
        tab1.setTabListener(this);
        getActionBar().addTab(tab1);

        ActionBar.Tab tab2 = getActionBar().newTab();
        tab2.setTag(ListType.VIEWED);
        tab2.setText(ListType.VIEWED.stringId);
        tab2.setTabListener(this);
        getActionBar().addTab(tab2);

        ActionBar.Tab tab3 = getActionBar().newTab();
        tab3.setTag(ListType.POSTED);
        tab3.setText(ListType.POSTED.stringId);
        tab3.setTabListener(this);
        getActionBar().addTab(tab3);

        if (savedInstanceState != null) {
            int savedIndex = savedInstanceState.getInt(PersistedSetting.SAVED_INDEX.toString());
            getActionBar().setSelectedNavigationItem(savedIndex);
            mSearchQuery = savedInstanceState.getString(PersistedSetting.SEARCH_QUERY.toString());
        }
        else {
            mSearchQuery = null;
        }

        //handleIntent(getIntent());
    }

    private static enum PersistedSetting {
        SAVED_INDEX,
        SEARCH_QUERY
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PersistedSetting.SAVED_INDEX.toString(), getActionBar().getSelectedNavigationIndex());
        outState.putString(PersistedSetting.SEARCH_QUERY.toString(), mSearchQuery);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        //handleIntent(intent);
    }

    /*
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "query=[ " + query + " ]", Toast.LENGTH_SHORT).show();
            if (menu != null) {
                menu.findItem(R.id.menu_search).collapseActionView();
            }
            //doMySearch(query);

        }
    }
    */
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

    @Override
    public String searchQuery() {
        return mSearchQuery;
    }

    @Override
    public void onCancelPersistedQuery() {
        mSearchQuery = null;
        ((ThreadListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.thread_list))
                .clearSearchFilter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchItem = menu.findItem(R.id.menu_search);
        mSearchItem.setOnActionExpandListener(searchActionExpandListener);
        mSearchView = (SearchView) mSearchItem.getActionView();
        // Assumes current activity is the searchable activity
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        mSearchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    private MenuItem.OnActionExpandListener searchActionExpandListener = new MenuItem.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            onCancelPersistedQuery();
            isSearchExpanded = true;
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            return true;
        }
    };

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            isSearchExpanded = false;
            mSearchQuery = query;
            ((ThreadListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.thread_list))
                    .persistQuery();
            //mSearchView.setIconified(true);
            mSearchItem.collapseActionView();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (isSearchExpanded) {
                ((ThreadListFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.thread_list))
                        .filter(newText);
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                new AddThreadFragment().show(getFragmentManager(), AddThreadFragment.class.getSimpleName());
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ((ThreadListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.thread_list))
                .onChangeListType((ListType) tab.getTag());
    }

    @Override public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ((ThreadListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.thread_list))
                .onRefreshList();
    }

    private class AddThreadFragment extends DialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.new_thread)
                    .setView(getLayoutInflater().inflate(R.layout.dialog_add_thread, null))
                    .setPositiveButton(R.string.add, addThreadListener)
                    .setNeutralButton(R.string.attach, attachToThreadListener)
                    .setNegativeButton(R.string.cancel, null);

            AlertDialog dialog = builder.create();
            return dialog;
        }

        private AlertDialog.OnClickListener addThreadListener = new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
            }
        };

        private AlertDialog.OnClickListener attachToThreadListener = new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getActivity(), "attach", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
