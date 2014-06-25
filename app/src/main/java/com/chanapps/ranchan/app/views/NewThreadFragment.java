package com.chanapps.ranchan.app.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadItem;

/**
 * A fragment representing a single Thread detail screen.
 * This fragment is either contained in a {@link com.chanapps.ranchan.app.controllers.ThreadListActivity}
 * in two-pane mode (on tablets) or a {@link com.chanapps.ranchan.app.controllers.ThreadDetailActivity}
 * on handsets.
 */
public class NewThreadFragment extends Fragment {

    private Callbacks mCallbacks = sDummyCallbacks;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewThreadFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_thread, container, false);

        EditText editText = (EditText) view.findViewById(R.id.new_thread_content);
        editText.setOnEditorActionListener(editorActionListener);

        return view;
    }

    TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                postNewThread();
                handled = true;
            }
            return handled;
        }
    };

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

    public interface Callbacks {
        public void postNewThread(String threadContent, boolean isNSFW, String localImagePath);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void postNewThread(String threadContent, boolean isNSFW, String localImagePath) {
        }
    };

    public void postNewThread() {
        String threadContentText = ((EditText)getView().findViewById(R.id.new_thread_content)).getText().toString().trim();
        boolean isNSFW = ((CheckBox)getView().findViewById(R.id.new_thread_nsfw)).isChecked();
        String localImagePath = null;
        mCallbacks.postNewThread(threadContentText, isNSFW, localImagePath);
    }

}
