package net.atlassc.gesturelocker.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.github.sevenheaven.gesturelock.widget.GestureLock;
import net.atlassc.gesturelocker.app.ApplicationExtend;
import net.atlassc.gesturelocker.app.R;
import net.atlassc.gesturelocker.app.widget.GestureLockUtils;

import java.util.ArrayList;
import java.util.List;


public class SetupPatternActivity extends ActionBarActivity implements GestureLock.OnGestureEventListener {

    private static final String TAG = SetupPatternActivity.class.getName();

    private GestureLock mLock;

    private int[] gestureContainer = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int count = 0;
    private TextView mHint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_pattern);

        getSupportActionBar().hide();

        mLock = ((GestureLock) findViewById(R.id.lock_view));
        mLock.setMode(GestureLock.MODE_EDIT);

        mHint = ((TextView) findViewById(R.id.setup_hint));
        mLock.setOnGestureEventListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setup_pattern, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            resetGestureLockToEditMode();
            Toast.makeText(SetupPatternActivity.this, (mLock.getMode() == GestureLock.MODE_NORMAL ? "MODE_NORMAL" : "MODE_EDIT"), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetGestureLockToEditMode() {
        mLock.setMode(GestureLock.MODE_EDIT);
        mLock.setMATCHED(true);
        gestureContainer = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        count = 0;
    }

    @Override
    public void onBlockSelected(int position) {
        Log.i(TAG, position + "");
        if (mLock.getMode() == GestureLock.MODE_EDIT) {
            gestureContainer[count++] = position;
        }
    }

    @Override
    public void onGestureEvent(boolean matched) {
        Log.i(TAG, "onGestureEvent " + matched);

        if (mLock.getMode() == GestureLock.MODE_EDIT) {
            List<Integer> tempList = new ArrayList<Integer>(0);
            for (int i = 0; i < gestureContainer.length; i++) {
                int gesture = gestureContainer[i];
                if (gesture < 0) {
                    break;
                }
                tempList.add(gesture);
            }
            int[] tempArray = new int[tempList.size()];
            for (int i = 0; i < tempList.size(); i++) {
                tempArray[i] = tempList.get(i);
            }
            mLock.setCorrectGesture(tempArray);
            mLock.setMode(GestureLock.MODE_NORMAL);
            mHint.setTextColor(getResources().getColor(R.color.app_white));
            mHint.setText("请再输入一次手势。");
        } else if (mLock.getMode() == GestureLock.MODE_NORMAL) {
            if (matched) {
                boolean result = GestureLockUtils.setGesture(SetupPatternActivity.this, mLock.getDefaultGestures());
                if (result) {
                    mLock.setTouchable(false);
                    mHint.setTextColor(getResources().getColor(R.color.app_green));
                    mHint.setText("手势创建成功！");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ApplicationExtend.setLocked(false);
                            finish();
                        }
                    }, 1 * 1000);
                }
            } else {
                mHint.setTextColor(getResources().getColor(R.color.app_red));
                mHint.setText("手势输入有误，请重新输入手势。");
                resetGestureLockToEditMode();
            }

        }
    }

    @Override
    public void onUnmatchedExceedBoundary() {

    }
}
