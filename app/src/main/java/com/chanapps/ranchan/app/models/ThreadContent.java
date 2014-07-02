package com.chanapps.ranchan.app.models;

import java.util.*;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class ThreadContent {

    static final boolean TEST_MODE = true;
    static List<ThreadItem> ITEMS = new ArrayList<ThreadItem>();
    static List<ThreadItem> DETAIL_ITEMS = new ArrayList<ThreadItem>();
    static Map<String, ThreadItem> ITEM_MAP = new HashMap<String, ThreadItem>();
    static Map<String, ThreadItem> DETAIL_ITEM_MAP = new HashMap<String, ThreadItem>();
    static Set<String> VIEWED = new HashSet<String>();
    static Set<String> POSTED = new HashSet<String>();

    static Object mLock = new Object();


    static void addItem(ThreadItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    static void addDetailItem(ThreadItem item) {
        DETAIL_ITEMS.add(item);
        DETAIL_ITEM_MAP.put(item.id, item);
    }

    static void addViewed(String id) {
        VIEWED.add(id);
    }

    static void addPosted(String id) {
        POSTED.add(id);
    }


    public static ThreadItem getItem(int position) {
        return ITEMS.get(position);
    }
    
    public static ThreadItem getDetailItem(int position) {
        return DETAIL_ITEMS.get(position);
    }

    public static ThreadItem getItem(String id) {
        return ITEM_MAP.get(id);
    }
    
    public static ThreadItem getDetailItem(String id) {
        return DETAIL_ITEM_MAP.get(id);
    }

    public static List<ThreadItem> getItems() {
        return ITEMS;
    }

    public static List<ThreadItem> getDetailItems() {
        return DETAIL_ITEMS;
    }

    public static Set<String> getViewed() {
        if (TEST_MODE) {
            return VIEWED;
        }
        else {
            throw new UnsupportedOperationException("viewed not yet implemented");
        }
    }

    public static Set<String> getPosted() {
        if (TEST_MODE) {
            return POSTED;
        }
        else {
            throw new UnsupportedOperationException("viewed not yet implemented");
        }
    }

}
