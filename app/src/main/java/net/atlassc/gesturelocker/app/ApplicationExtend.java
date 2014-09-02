package net.atlassc.gesturelocker.app;

import android.app.Application;

/**
 * Created by ShinChven on 14/9/1.
 */
public class ApplicationExtend extends Application{

    private static boolean locked =true;

    public static boolean isLocked() {
        return locked;
    }

    public static void setLocked(boolean locked) {
        ApplicationExtend.locked = locked;
    }
}
