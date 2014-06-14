package com.chanapps.ranchan.app.dummy;

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
        addItem(new ThreadItem("a8364469-3167-4573-b4c3-594d8bcb327e", "No waifu thread?  I shall correct that.", 45, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("7cd91383-6b8b-4432-a5fc-a06d63d561cd", ">tfw no gf", 187, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("286d6398-a9fa-4f19-aa11-c58888d798b7", "Ukraine is burning.  Let's celebrate.", 132, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("c96222a1-da6c-44b0-86c6-5fe17ddb41e8", "Porsche thread?  Porsche thread.", 53, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("19f13dc4-91cf-476a-9215-d76fe024e956", "hawt bods", 5, c.getTime(), true));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("8cdd1917-e37b-4385-b777-123c0017e123", "black chicks", 78, c.getTime(), true));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("2aeafc10-891b-499d-b806-85f14c72a8af", "ISIL ftw!", 3, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("ae748873-2ab2-4efe-a546-9024ccf760af", "Yotsuba goes to the beach", 85, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("a2b18964-8b93-4074-a3a0-f56dfa180d68", "Please post in my thread.", 0, c.getTime(), false));
        c.add(Calendar.HOUR_OF_DAY, -1);
        addItem(new ThreadItem("f2b46979-13f5-4357-8379-bc4d4fc38a2e", "Bikini thread", 167, c.getTime(), false));
    }

    private static void addItem(ThreadItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ThreadItem {
        private static final String THUMB_URL_FORMAT = "https://ranchan.org/t/%0d.jpg";
        private static final String PREVIEW_URL_FORMAT = "https://ranchan.org/i/%0d.jpg";
        private static final String FULL_URL_FORMAT = "https://ranchan.org/f/%0d.jpg";

        public String id;
        public String content;
        public int chats;
        public Date posted;
        public boolean adult;

        public ThreadItem(String id, String content, int chats, Date posted, boolean adult) {
            this.id = id;
            this.content = content;
            this.chats = chats;
            this.posted = posted;
            this.adult = adult;
        }

        public String thumbUrl() {
            return String.format(THUMB_URL_FORMAT, id);
        }

        public String previewUrl() {
            return String.format(PREVIEW_URL_FORMAT, id);
        }

        public String fullUrl() {
            return String.format(FULL_URL_FORMAT, id);
        }

        @Override
        public String toString() {
            return content;
        }

    }
}
