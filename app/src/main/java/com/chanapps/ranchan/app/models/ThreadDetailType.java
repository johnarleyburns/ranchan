package com.chanapps.ranchan.app.models;

import com.chanapps.ranchan.app.R;

/**
* Created by johnarleyburns on 21/06/14.
*/
public enum ThreadDetailType {
    CHATS(R.string.chats),
    IMAGES(R.string.image);
    public int stringId;
    ThreadDetailType(int stringId) {
        this.stringId = stringId;
    }
}
