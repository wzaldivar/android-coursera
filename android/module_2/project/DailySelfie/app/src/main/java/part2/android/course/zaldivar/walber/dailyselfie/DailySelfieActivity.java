package part2.android.course.zaldivar.walber.dailyselfie;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

public class DailySelfieActivity extends AppCompatActivity {
    private static final File DAILY_DIR = new File(Environment.getExternalStorageDirectory(), "DailySelfie");

    private boolean preparedDir() {
        return DAILY_DIR.mkdirs() || DAILY_DIR.isDirectory();
    }


    private boolean isWritable() {
        return preparedDir() && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_selfie);

        if (!preparedDir()) {
            Toast.makeText(this, getText(R.string.no_dir), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_selfie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_camera) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
