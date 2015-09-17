package zaldivar.walber.course.modern.art.rug;

import android.widget.FrameLayout;
import android.widget.SeekBar;

import java.util.Map;

// custom listener for color bar
public class OnColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private final Map<FrameLayout, RGBInformation> initialColors;

    public OnColorSeekBarChangeListener(Map<FrameLayout, RGBInformation> initialColors) {
        this.initialColors = initialColors;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        for (FrameLayout frame : initialColors.keySet()) {
            // modify colors of managed frames
            frame.setBackgroundColor(initialColors.get(frame).getRGBColor(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
