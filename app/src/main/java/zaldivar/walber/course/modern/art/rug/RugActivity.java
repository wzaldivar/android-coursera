package zaldivar.walber.course.modern.art.rug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class RugActivity extends AppCompatActivity {
//    private SeekBar colorSeekBar;
//    private OnColorSeekBarChangeListener colorSeekBarChangeListener;
//    private final ArrayList<FrameLayout> frameLayouts = new ArrayList<FrameLayout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rug);

//        frameLayouts.add((FrameLayout) findViewById(R.id.frame00));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame01));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame02));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame03));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame04));
//
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame10));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame11));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame12));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame13));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame14));
//
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame20));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame21));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame22));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame23));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame24));
//
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame30));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame31));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame32));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame33));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame34));
//
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame40));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame41));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame42));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame43));
//        frameLayouts.add((FrameLayout) findViewById(R.id.frame44));
//
//        colorSeekBarChangeListener = new OnColorSeekBarChangeListener(frameLayouts);
//
//        colorSeekBar = (SeekBar) findViewById(R.id.colorSeekBar);
//        colorSeekBar.setMax(0xff);
//        colorSeekBar.setOnSeekBarChangeListener(colorSeekBarChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rug, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_information) {
            new MomaDialog().show(getFragmentManager(), "MOMA Dialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
