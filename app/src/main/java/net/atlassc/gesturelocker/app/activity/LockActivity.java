package net.atlassc.gesturelocker.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.github.sevenheaven.gesturelock.widget.GestureLock;
import com.github.sevenheaven.gesturelock.widget.GestureLockView;
import net.atlassc.gesturelocker.app.ApplicationExtend;
import net.atlassc.gesturelocker.app.R;
import net.atlassc.gesturelocker.app.widget.GestureLockUtils;


public class LockActivity extends ActionBarActivity {
    private GestureLock gestureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        getSupportActionBar().hide();

        gestureView = (GestureLock) findViewById(R.id.gesture_lock);
        gestureView.setCorrectGesture(GestureLockUtils.getGesture(this));
        gestureView.setMode(GestureLockView.ARROW_BOTTOM);
        // gestureView.setMode(GestureLock.MODE_EDIT);
        // gestureView.setMode(GestureLockView.MODE_ERROR | GestureLockView.ARROW_BOTTOM_LEFT);
        gestureView.setOnGestureEventListener(new GestureLock.OnGestureEventListener() {

            @Override
            public void onGestureEvent(boolean matched) {
                Toast.makeText(LockActivity.this, "Match:" + matched, Toast.LENGTH_SHORT).show();
                if (matched) {
                    gestureView.invalidate();
                    ApplicationExtend.setLocked(false);
                    LockActivity.this.finish();
                }
            }

            @Override
            public void onUnmatchedExceedBoundary() {
                Toast.makeText(LockActivity.this, "输入5次错误!15秒后才能输入", Toast.LENGTH_SHORT).show();
//				gestureView.setTouchable(false);
//				new Handler().postDelayed(new Runnable(){
//					@Override
//					public void run(){
//                        gestureView.setTouchable(true);
//					}
//				}, 15000);

            }

            @Override
            public void onBlockSelected(int position) {
                Log.d("position", position + "");
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                GestureLockUtils.setDrawTrack(this, !GestureLockUtils.isDrawTrack(this));
                Toast.makeText(this, "draw pattern" + GestureLockUtils.isDrawTrack(this), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(this, "应用已锁定，请输入手势解锁", Toast.LENGTH_SHORT).show();
    }

}
