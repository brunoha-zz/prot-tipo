package com.github.utils;

import android.os.Build;

public class Utils {
    public static boolean isVersionOlderThanLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1;
    }
}
