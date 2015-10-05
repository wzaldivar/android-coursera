package part2.android.course.zaldivar.walber.dailyselfie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailySelfieActivity extends AppCompatActivity {
    // selfies storage dir
    private static final File DAILY_DIR = new File(Environment.getExternalStorageDirectory(), "DailySelfie");
    private static final int REQUEST_TAKE_SELFIE = 1;
    private SelfieAdapter adapter;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private File selfieFile;
    private boolean filled = false;

    // check access to selfies storage dir
    private boolean preparedDir() {
        return DAILY_DIR.mkdirs() || DAILY_DIR.isDirectory();
    }

    // check write permission to selfies storage dir
    private boolean isWritable() {
        return preparedDir() && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_selfie);

        ListView listView = (ListView) findViewById(R.id.selfie_list);
        adapter = new SelfieAdapter(this);

        listView.setAdapter(adapter);

        if (!preparedDir()) {
            Toast.makeText(this, getText(R.string.no_dir), Toast.LENGTH_LONG).show();
            finish();
        }

        adapter.fill(DAILY_DIR);
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

            if (isWritable()) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    selfieFile = new File(DAILY_DIR, DATE_FORMAT.format(new Date()) + ".jpg");
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(selfieFile));
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_SELFIE);
                }

            } else {
                Toast.makeText(this, R.string.cant_write, Toast.LENGTH_LONG).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_SELFIE && resultCode == RESULT_OK) {
            adapter.add(new SelfieInfo(selfieFile));

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(selfieFile);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
