package com.chanapps.ranchan.app.models;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A dummy item representing a piece of content.
 */
public class ThreadItem {

    private static final boolean TEST_MODE = true;

    public static final int FLAG_ADULT = 0x01;

    private static final String THUMB_URL_FORMAT = "https://ranchan.org/t/%0d.jpg";  // 96x96
    private static final String PREVIEW_URL_FORMAT = "https://ranchan.org/i/%0d.jpg"; // 960x960
    private static final String FULL_URL_FORMAT = "https://ranchan.org/f/%0d.jpg"; // 3MB max ???

    public String id;
    public String parentId;
    public String content;
    public Date date;
    public int imageBytes;
    public int flags;
    public int chats;
    public int images;

    public ThreadItem(String id, String parentId, Date date, String content, int imageBytes, int flags, int chats, int images) {
        this.id = id;
        this.parentId = parentId;
        this.content = content;
        this.date = date;
        this.imageBytes = imageBytes;
        this.flags = flags;
        this.chats = chats;
        this.images = images;
    }

    public boolean adult() {
        return (flags & FLAG_ADULT) > 0;
    }

    public boolean hasImage() {
        return imageBytes > 0;
    }

    public String shortDate(Context context) {
        Calendar todayCal = Calendar.getInstance();
        int todayDay = todayCal.get(Calendar.DAY_OF_YEAR);
        int todayYear = todayCal.get(Calendar.YEAR);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        int dateDay = dateCal.get(Calendar.DAY_OF_YEAR);
        int dateYear = dateCal.get(Calendar.YEAR);
        if (dateDay == todayDay && dateYear == todayYear) {
            return SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT).format(date);
        }
        else if (dateYear == todayYear) {
            return DateUtils.formatDateTime(context, date.getTime(),
                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_YEAR | DateUtils.FORMAT_ABBREV_ALL);
        }
        else {
            return String.valueOf(dateYear);
        }
    }

    public String thumbUrl() {
        if (!hasImage()) {
            return null;
        }
        if (TEST_MODE) {
            return TEST_THUMBS.get(id);
        }
        else {
            return String.format(THUMB_URL_FORMAT, id);
        }
    }

    public String previewUrl() {
        if (!hasImage()) {
            return null;
        }
        if (TEST_MODE) {
            if (TEST_IMAGES.containsKey(id)) {
                return TEST_IMAGES.get(id);
            }
            else {
                return TEST_THUMBS.get(id);
            }
        }
        else {
            return String.format(PREVIEW_URL_FORMAT, id);
        }
    }

    public String fullUrl() {
        return String.format(FULL_URL_FORMAT, id);
    }

    @Override
    public String toString() {
        return content;
    }

    private static final Map<String, String> TEST_THUMBS = build(

            "a8364469-3167-4573-b4c3-594d8bcb327e",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Figure_in_Manga_style_variation_1.png/240px-Figure_in_Manga_style_variation_1.png",

            "7cd91383-6b8b-4432-a5fc-a06d63d561cd",
            "https://upload.wikimedia.org/wikipedia/commons/5/54/Solitude_%281894076214%29.jpg",

            "286d6398-a9fa-4f19-aa11-c58888d798b7",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Protesters_on_the_barricades%2C_seen_through_the_lights_of_fire_built._Euromaidan_Protests.jpg/320px-Protesters_on_the_barricades%2C_seen_through_the_lights_of_fire_built._Euromaidan_Protests.jpg",

            "c96222a1-da6c-44b0-86c6-5fe17ddb41e8",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Porsche.jpg/320px-Porsche.jpg",

            "19f13dc4-91cf-476a-9215-d76fe024e956",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Tremendo_gordo.jpg/320px-Tremendo_gordo.jpg",

            "8cdd1917-e37b-4385-b777-123c0017e123",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Black_woman_dancing_%281634389290%29.jpg/161px-Black_woman_dancing_%281634389290%29.jpg",

            "2aeafc10-891b-499d-b806-85f14c72a8af",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Flag_of_Islamic_State_of_Iraq.svg/200px-Flag_of_Islamic_State_of_Iraq.svg.png",

            "ae748873-2ab2-4efe-a546-9024ccf760af",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Anime_girl_at_beach.jpg/165px-Anime_girl_at_beach.jpg",

            "a2b18964-8b93-4074-a3a0-f56dfa180d68",
            "https://upload.wikimedia.org/wikipedia/commons/3/3c/Graffiti_Here_Please.jpg",

            "f2b46979-13f5-4357-8379-bc4d4fc38a2e",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Topless_woman_walking_on_Coral_Beach%2C_Jamaica.jpg/160px-Topless_woman_walking_on_Coral_Beach%2C_Jamaica.jpg",

            "8b67d0c3-d7f0-4c0c-b6f9-66d5d3e3081b",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Sven_Melander.jpg/192px-Sven_Melander.jpg",

            "6cef17c6-9719-486a-8535-97f2e4921122",
            "https://upload.wikimedia.org/wikipedia/commons/c/c3/Chavosh.jpg",

            "7914c43c-2147-46c8-adda-ab21466b9f86",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Dota-juku_01.JPG/320px-Dota-juku_01.JPG"
    );

    private static final Map<String, String> TEST_IMAGES = build(

            "7914c43c-2147-46c8-adda-ab21466b9f86",
            "https://upload.wikimedia.org/wikipedia/commons/4/4b/Dota-juku_01.JPG"

    );

    public static HashMap<String, String> build(String... data) {
        HashMap<String, String> result = new HashMap<String, String>();

        if (data.length % 2 != 0)
            throw new IllegalArgumentException("Odd number of arguments");

        String key = null;
        Integer step = -1;

        for (String value : data) {
            step++;
            switch (step % 2) {
                case 0:
                    if (value == null)
                        throw new IllegalArgumentException("Null key value");
                    key = value;
                    continue;
                case 1:
                    result.put(key, value);
                    break;
            }
        }

        return result;
    }

}
