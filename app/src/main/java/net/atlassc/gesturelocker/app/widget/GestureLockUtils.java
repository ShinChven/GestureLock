package net.atlassc.gesturelocker.app.widget;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShinChven on 14/8/29.
 */
public class GestureLockUtils {


    private static final String NAME_GESTURE_CONFIG = "gesture_config";
    private static final String KEY_DRAW_TRACK = "drawTrack";
    private static final String KEY_GESTURE_STORE = "gestureStore";

    public static boolean isDrawTrack(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME_GESTURE_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_DRAW_TRACK, true);
    }

    public static void setDrawTrack(Context context, boolean drawTrack) {
        SharedPreferences sp = context.getSharedPreferences(NAME_GESTURE_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_DRAW_TRACK, drawTrack);
        editor.apply();
    }

    public static int[] getGesture(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME_GESTURE_CONFIG, Context.MODE_PRIVATE);
        try {
            JSONArray jsonArray = new JSONArray(sp.getString(KEY_GESTURE_STORE, "{}"));
            int[] gesture = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                gesture[i] = jsonArray.getInt(i);
            }
            return gesture;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean setGesture(Context context, int[] gesture) {
        try {
            SharedPreferences sp = context.getSharedPreferences(NAME_GESTURE_CONFIG, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            List<Integer> tempList = new ArrayList<Integer>(0);
            for (int i = 0; i < gesture.length; i++) {
                tempList.add(gesture[i]);
            }
            JSONArray array = new JSONArray(tempList);
            editor.putString(KEY_GESTURE_STORE, array.toString());
            editor.apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clearGesture(Context context){
        SharedPreferences sp = context.getSharedPreferences(NAME_GESTURE_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(KEY_GESTURE_STORE);
        editor.apply();
    }
}
