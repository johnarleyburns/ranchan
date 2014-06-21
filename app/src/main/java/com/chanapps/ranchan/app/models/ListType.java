package com.chanapps.ranchan.app.models;

import com.chanapps.ranchan.app.R;

/**
* Created by johnarleyburns on 21/06/14.
*/
public enum ListType {
    HOME(R.string.home),
    VIEWED(R.string.viewed),
    POSTED(R.string.posted);
    public int stringId;
    ListType(int stringId) {
        this.stringId = stringId;
    }
}
