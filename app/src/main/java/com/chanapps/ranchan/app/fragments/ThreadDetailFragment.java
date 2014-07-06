package com.chanapps.ranchan.app.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.*;
import android.widget.*;


import com.chanapps.ranchan.app.R;
import com.chanapps.ranchan.app.adapters.ThreadDetailAdapter;
import com.chanapps.ranchan.app.models.ThreadContent;
import com.chanapps.ranchan.app.models.ThreadContentTestHarness;
import com.chanapps.ranchan.app.models.ThreadDetailType;
import com.chanapps.ranchan.app.models.ThreadItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A fragment representing a single Thread detail screen.
 * This fragment is either contained in a {@link com.chanapps.ranchan.app.controllers.ThreadListActivity}
 * in two-pane mode (on tablets) or a {@link com.chanapps.ranchan.app.controllers.ThreadDetailActivity}
 * on handsets.
 */
public class ThreadDetailFragment extends ListFragment implements View.OnCreateContextMenuListener {

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

        //registerForContextMenu(list);
        setupContextMenu(list);

        /*
            if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.thread_detail)).setText(mItem.content);
        }
        */

        return view;
    }

    private void setupContextMenu(ListView listView) {
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                Toast.makeText(getActivity(), "pos=" + position + " checked=" + checked, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                int count =  getListView().getCheckedItemCount();
                Toast.makeText(getActivity(), "checked=" + count, Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.menu_reply:
                        reply();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    case R.id.menu_download:
                        download();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    case R.id.menu_delete:
                        delete();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.thread_detail_context_menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });
    }

    private List<ThreadItem> checkedItems() {
        List<Integer> checked = checkedPositions();
        List<ThreadItem> items = new ArrayList<ThreadItem>(checked.size());
        for (int pos : checked) {
            items.add(mAdapter.getItem(pos));
        }
        return items;
    }

    private List<Integer> checkedPositions() {
        List<Integer> list = new ArrayList<Integer>();
        SparseBooleanArray a = getListView().getCheckedItemPositions();
        for (int i = 0; i < a.size(); i++) {
            int pos = a.keyAt(i);
            if (a.get(pos)) {
                list.add(pos);
            }
        }
        return list;
    }

    private void reply() {
        String ids = "";
        for (int position : checkedPositions()) {
            ids += mAdapter.getItem(position).id + " ";
        }
        Toast.makeText(getActivity(), "reply " + ids, Toast.LENGTH_SHORT).show();
        showReplyDialog();
    }

    private void download() {
        String ids = "";
        for (int position : checkedPositions()) {
            ids += mAdapter.getItem(position).id + " ";
        }
        Toast.makeText(getActivity(), "download " + ids, Toast.LENGTH_SHORT).show();
    }

    private void delete() {
        String ids = "";
        for (int position : checkedPositions()) {
            ids += mAdapter.getItem(position).id + " ";
        }
        Toast.makeText(getActivity(), "delete " + ids, Toast.LENGTH_SHORT).show();
    }

    private void showReplyDialog() {
        View layout = ((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.dialog_reply_thread_item, null);
        String text = replyText(checkedItems());
        final EditText reply = (EditText)layout.findViewById(R.id.thread_text);
        reply.setText(text);
        int len = reply.getText().length();
        reply.setSelection(len, len);
        (new AlertDialog.Builder(getActivity()))
                .setTitle(null)
                .setView(layout)
                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doReply(reply.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show();
    }

    private String replyText(List<ThreadItem> items) {
        StringBuilder sb = new StringBuilder();
        for (ThreadItem item : items) {
            String content = item.content.trim().replaceAll("\n", "\n>");
            sb.append(">")
                    .append(content)
                    .append("\n\n");
        }
        return sb.toString();
    }

    private void doReply(String replyText) {
        Toast.makeText(getActivity(), "replying...", Toast.LENGTH_SHORT).show();
    }

    private void setTrashView(View view, final String id) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new AlertDialog.Builder(getActivity()))
                        .setTitle(R.string.trash_reason)
                        .setItems(R.array.trash_reasons, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, final int which) {
                                String[] array = getActivity().getResources().getStringArray(R.array.trash_reasons);
                                int len = array.length;
                                if (which == len - 1) {
                                    return; // cancel was pressed
                                } else {
                                    String reason = array[which];
                                    doTrash(id, which, reason);
                                }
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private void doTrash(String id, int which, String reason) {
        Toast.makeText(getActivity(), "trashing '" + id + "' for " +  reason + "...", Toast.LENGTH_SHORT).show();
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
