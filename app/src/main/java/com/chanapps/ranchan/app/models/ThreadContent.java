package com.chanapps.ranchan.app.models;

import java.util.*;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ThreadContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<ThreadItem> ITEMS = new ArrayList<ThreadItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, ThreadItem> ITEM_MAP = new HashMap<String, ThreadItem>();

    static {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("a8364469-3167-4573-b4c3-594d8bcb327e", "No waifu thread?  I shall correct that.", 45, 17, c.getTime(), false, true, true));
        c.add(Calendar.HOUR_OF_DAY, -2);
        addItem(new ThreadItem("7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">tfw no gf", 187, 32, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -4);
        addItem(new ThreadItem("286d6398-a9fa-4f19-aa11-c58888d798b7", "Ukraine is burning.  Let's celebrate.", 132, 12, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -8);
        addItem(new ThreadItem("c96222a1-da6c-44b0-86c6-5fe17ddb41e8", "Porsche thread?  Porsche thread.", 53, 46, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -16);
        addItem(new ThreadItem("19f13dc4-91cf-476a-9215-d76fe024e956", "hawt bods", 5, 3, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -32);
        addItem(new ThreadItem("8cdd1917-e37b-4385-b777-123c0017e123", "black chicks", 78, 52, c.getTime(), true, true, false));
        c.add(Calendar.HOUR_OF_DAY, -64);
        addItem(new ThreadItem("2aeafc10-891b-499d-b806-85f14c72a8af", "ISIL ftw!", 3, 1, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -128);
        addItem(new ThreadItem("ae748873-2ab2-4efe-a546-9024ccf760af", "Yotsuba goes to the beach, the latest manga is out, I'm dumping all 200 pages here... enjoy!", 85, 83, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -512);
        addItem(new ThreadItem("a2b18964-8b93-4074-a3a0-f56dfa180d68", "Please post in my thread.", 0, 0, c.getTime(), false, true, false));
        c.add(Calendar.HOUR_OF_DAY, -1024);
        addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc38a2e", "Let's talk about meditation.  Lately I've been eating only fruits and vegetables and meditating, is this going to give me superpowers?", 38, 0, c.getTime(), false, false, false));
        c.add(Calendar.HOUR_OF_DAY, -10000);
        addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc38a2e", "Bikini thread", 167, 150, c.getTime(), true, true, false));
    }

    private static void addItem(ThreadItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

}
