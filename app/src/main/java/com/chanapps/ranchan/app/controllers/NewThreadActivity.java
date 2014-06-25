package com.chanapps.ranchan.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.views.NewThreadFragment;


/**
 * An activity representing a single Thread detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link com.chanapps.ranchan.app.controllers.ThreadListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link com.chanapps.ranchan.app.views.NewThreadFragment}.
 */
public class NewThreadActivity extends FragmentActivity implements NewThreadFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_detail);

        // Show the Up button in the action bar.
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
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(NewThreadFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(NewThreadFragment.ARG_ITEM_ID));
            NewThreadFragment fragment = new NewThreadFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.thread_detail_container, fragment, NewThreadFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_thread_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
            case R.id.menu_send:
                ((NewThreadFragment)getSupportFragmentManager()
                        .findFragmentByTag(NewThreadFragment.class.getSimpleName()))
                        .postNewThread();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void postNewThread(String threadContent, boolean isNSFW, String localImagePath) {
        Toast.makeText(this, threadContent + "\nisNSFW=[" + isNSFW + "]", Toast.LENGTH_SHORT).show();
        finish();
    }

}
