package net.atlassc.gesturelocker.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import net.atlassc.gesturelocker.app.ApplicationExtend;
import net.atlassc.gesturelocker.app.widget.GestureLockUtils;

/**
 * Created by ShinChven on 14/9/1.
 */
public class BaseActionBarActivity extends ActionBarActivity {


    private static final String TAG = "BaseActionBarActivity";

    public static final String IS_LOCK = "is_unlock";
    private boolean lock = true;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume " + getLocalClassName() + " " + lock);
        if (ApplicationExtend.isLocked()&& GestureLockUtils.getGesture(this)!=null) {
            Intent intent = new Intent(this, LockActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate " + getLocalClassName() + " " + lock);

        unlockActivity();
    }

    private void unlockActivity() {
        try {
            ApplicationExtend.setLocked(getIntent().getExtras().getBoolean(IS_LOCK, true));
        } catch (Exception e) {
            //ApplicationExtend.setLocked(true);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        intent.putExtra(IS_LOCK, false);
        Log.i(TAG, "startActivity " + getLocalClassName() + " " + lock);

        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra(IS_LOCK, false);
        Log.i(TAG, "startActivityForResult " + getLocalClassName() + " " + lock);

        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop " + getLocalClassName() + " " + lock);
        ApplicationExtend.setLocked(lock);
    }

    @Override
    public void finish() {
        super.finish();
        ApplicationExtend.setLocked(false);
        Log.i(TAG, "finish " + getLocalClassName() + " " + lock);

    }

    @Override
    public void onBackPressed() {
        ApplicationExtend.setLocked(false);
        super.onBackPressed();
        Log.i(TAG, "onBackPressed " + getLocalClassName() + " " + lock);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            lock = false;
            ApplicationExtend.setLocked(false);
            Log.i(TAG, "home " + getLocalClassName() + " " + lock);
        }
        return super.onOptionsItemSelected(item);
    }
}
