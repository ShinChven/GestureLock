package net.atlassc.gesturelocker.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import net.atlassc.gesturelocker.app.R;


public class MainActivity extends AutoLockActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.setup_pattern).setOnClickListener(this);
        findViewById(R.id.lock_demo).setOnClickListener(this);
        findViewById(R.id.open_demo).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Class type = null;
        switch (v.getId()) {
            case R.id.setup_pattern:
                type = SetupPatternActivity.class;
                break;
            case R.id.lock_demo:
                type = LockActivity.class;
                break;
            case R.id.open_demo:
                type = DemoActivity.class;
                break;
            default:
                break;
        }
        try {
            intent = new Intent(this, type);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
