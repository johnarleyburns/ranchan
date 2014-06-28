package com.chanapps.ranchan.app.models;

import java.util.*;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class ThreadContent {

    private static final boolean TEST_MODE = true;

    private static List<ThreadItem> ITEMS = new ArrayList<ThreadItem>();
    private static List<ThreadItem> DETAIL_ITEMS = new ArrayList<ThreadItem>();
    private static Map<String, ThreadItem> ITEM_MAP = new HashMap<String, ThreadItem>();
    private static Map<String, ThreadItem> DETAIL_ITEM_MAP = new HashMap<String, ThreadItem>();
    private static Set<String> VIEWED = new HashSet<String>();
    private static Set<String> POSTED = new HashSet<String>();

    private static Object mLock = new Object();

    public static void loadList() {
        synchronized (mLock) {
            ITEMS.clear();
            ITEM_MAP.clear();
            VIEWED.clear();
            POSTED.clear();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR_OF_DAY, -1);
            addItem(new ThreadItem("7cd91383-6b8b-4432-a5fc-a06d63d561cd", null, c.getTime(), "Hey it was my birthday yesterday so I got a new 1tb hard drive which I'm going to dedicate to all my games meaning I need to reinstall my games, plus I got a bunch of money to spend on new games so give me some good recommendation", 134134, 0, 187, 32));
            c.add(Calendar.HOUR_OF_DAY, -2);
            addItem(new ThreadItem("a8364469-3167-4573-b4c3-594d8bcb327e", null, c.getTime(), "No waifu thread?  I shall correct that.", 33123, 0, 45, 17));
            c.add(Calendar.HOUR_OF_DAY, -4);
            addItem(new ThreadItem("286d6398-a9fa-4f19-aa11-c58888d798b7", null, c.getTime(), "Ukraine is burning.  Let's celebrate.", 34234, 0, 132, 12));
            c.add(Calendar.HOUR_OF_DAY, -8);
            addItem(new ThreadItem("c96222a1-da6c-44b0-86c6-5fe17ddb41e8", null, c.getTime(), "Porsche thread?  Porsche thread.", 454532, 0, 53, 46));
            c.add(Calendar.HOUR_OF_DAY, -16);
            addItem(new ThreadItem("19f13dc4-91cf-476a-9215-d76fe024e956", null, c.getTime(), "hawt bods", 212423, 0, 5, 3));
            c.add(Calendar.HOUR_OF_DAY, -32);
            addItem(new ThreadItem("8cdd1917-e37b-4385-b777-123c0017e123", null, c.getTime(), "black chicks", 235455, ThreadItem.FLAG_ADULT, 78, 52));
            c.add(Calendar.HOUR_OF_DAY, -64);
            addItem(new ThreadItem("2aeafc10-891b-499d-b806-85f14c72a8af", null, c.getTime(), "ISIL ftw!", 12353, 0, 3, 1));
            c.add(Calendar.HOUR_OF_DAY, -128);
            addItem(new ThreadItem("ae748873-2ab2-4efe-a546-9024ccf760af", null, c.getTime(), "Yotsuba goes to the beach, the latest manga is out, I'm dumping all 200 pages here... enjoy!", 1343234, 0, 85, 83));
            c.add(Calendar.HOUR_OF_DAY, -512);
            addItem(new ThreadItem("a2b18964-8b93-4074-a3a0-f56dfa180d68", null, c.getTime(), "Please post in my thread.", 23423, 0, 0, 0));
            c.add(Calendar.HOUR_OF_DAY, -1024);
            addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc37a2e", null, c.getTime(), "Let's talk about meditation.  Lately I've been eating only fruits and vegetables and meditating, is this going to give me superpowers?", 0, 0, 38, 0));
            c.add(Calendar.HOUR_OF_DAY, -10000);
            addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc38a2e", null, c.getTime(), "Bikini thread", 234892, ThreadItem.FLAG_ADULT, 167, 150));

            addViewed(getItem(1).id);
            addViewed(getItem(2).id);
            addViewed(getItem(9).id);
            addViewed(getItem(10).id);

            addPosted(getItem(1).id);
            addPosted(getItem(2).id);
        }
    }

    public static void loadDetail() {
        synchronized (mLock) {
            DETAIL_ITEMS.clear();
            DETAIL_ITEM_MAP.clear();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR_OF_DAY, -1);
            addDetailItem(new ThreadItem("7cd91383-6b8b-4432-a5fc-a06d63d561cd", null, c.getTime(), "Hey it was my birthday yesterday so I got a new 1tb hard drive which I'm going to dedicate to all my games meaning I need to reinstall my games, plus I got a bunch of money to spend on new games so give me some good recommendation", 65343, 0, 187, 32));
            c.add(Calendar.MINUTE, 1);
            addDetailItem(new ThreadItem("a19f590a-eb73-495d-8a6b-c882c106e796", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), "help the poor beggers", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 2);
            addDetailItem(new ThreadItem("cc6795e3-fa9d-4cb0-9cbc-a3766cd3ccaf", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), ">help the poor beggers\nI would but I have done that stuff before and I just feel like no one is grateful enough, sorry", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 2);
            addDetailItem(new ThreadItem("8b67d0c3-d7f0-4c0c-b6f9-66d5d3e3081b", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), "Isn't Sven 1/10", 12324, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            addDetailItem(new ThreadItem("7914c43c-2147-46c8-adda-ab21466b9f86", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), "Rate my favorite dota!", 3452234, 0, 0, 0));
            c.add(Calendar.MINUTE, 5);
            addDetailItem(new ThreadItem("91cd097e-9953-47ae-aec0-cd410bfb6c33", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), ">Rate my favorite dota!\ncan i get these stuff for free or do i have to pay for it?  i new into dota 2", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            addDetailItem(new ThreadItem("ccde0817-5d54-4e22-8c39-6fc10b6f24f2", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), ">can i get these stuff for free...\nJust play for a while.  You'll get some drops.\n\nUnless you want that immortal, you'll need to pay", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            addDetailItem(new ThreadItem("8f7fbea3-8273-42c7-895c-40602780e0a6", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), ">Playing DOTA instead of a superior MOBA\nFaggot Tier/10", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 1);
            addDetailItem(new ThreadItem("520dc9bb-ad7b-4570-a7e6-3d49ae9db277", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), "500+ games ALL anti-mage.  Get. The. Fuck. On my level you fucking n00bz.", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 3);
            addDetailItem(new ThreadItem("c04cbb80-9c55-4096-9a56-788e276835cd", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), ">Faggot Tier/10\nPlaying MOBA at all.  Stop acting elitist over such a shitty type of game.", 0, 0, 0, 0));
            c.add(Calendar.MINUTE, 2);
            addDetailItem(new ThreadItem("6cef17c6-9719-486a-8535-97f2e4921122", "7cd91383-6b8b-4432-a5fc-a06d63d561cd", c.getTime(), "U wot m8?", 4712, 0, 0, 0));
        }
    }

    private static void addItem(ThreadItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static void addDetailItem(ThreadItem item) {
        DETAIL_ITEMS.add(item);
        DETAIL_ITEM_MAP.put(item.id, item);
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

    private static void addViewed(String id) {
        VIEWED.add(id);
    }

    private static void addPosted(String id) {
        POSTED.add(id);
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
