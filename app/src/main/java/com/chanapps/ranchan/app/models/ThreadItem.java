package com.chanapps.ranchan.app.models;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A dummy item representing a piece of content.
 */
public class ThreadItem extends ThreadItemModel {

    private static final boolean TEST_MODE = true;

    public static final int FLAG_ADULT = 0x01;

    private static final String THUMB_URL_FORMAT = "https://ranchan.org/t/%0d.jpg";  // 512x512
    private static final String IMAGE_URL_FORMAT = "https://ranchan.org/i/%0d.jpg"; // full image

    public ThreadItem(String id, String parentId, String content, String nickname,
                      Date date, int imageBytes, int width, int height,
                      int flags, int chats, int images) {
        this.id = id;
        this.parentId = parentId;
        this.content = content;
        this.nickname = nickname;
        this.date = date;
        this.imageBytes = imageBytes;
        this.width = width;
        this.height = height;
        this.flags = flags;
        this.chats = chats;
        this.images = images;
    }

    public boolean adult() {
        return (flags & FLAG_ADULT) > 0;
    }

    public boolean hasImage() {
        return imageBytes > 0 && width > 0 && height > 0;
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
            return ThreadContentTestHarness.TEST_THUMBS.get(id);
        }
        else {
            return String.format(THUMB_URL_FORMAT, id);
        }
    }

    public String imageUrl() {
        if (!hasImage()) {
            return null;
        }
        if (TEST_MODE) {
            if (ThreadContentTestHarness.TEST_IMAGES.containsKey(id)) {
                return ThreadContentTestHarness.TEST_IMAGES.get(id);
            }
            else {
                return ThreadContentTestHarness.TEST_THUMBS.get(id);
            }
        }
        else {
            return String.format(IMAGE_URL_FORMAT, id);
        }
    }

    @Override
    public String toString() {
        return content;
    }

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
