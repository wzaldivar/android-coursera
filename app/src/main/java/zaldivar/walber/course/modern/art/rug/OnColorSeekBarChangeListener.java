package zaldivar.walber.course.modern.art.rug;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wyro on 18/08/15.
 */
public class OnColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private final Map<FrameLayout, Integer> startColors = new HashMap<FrameLayout, Integer>();

    public OnColorSeekBarChangeListener(ArrayList<FrameLayout> frameLayouts) {
        for (FrameLayout frameLayout : frameLayouts) {
            int color;
            Drawable background = frameLayout.getBackground();
            if (background instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) background;
                color = colorDrawable.getColor();
                startColors.put(frameLayout, color);
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Set<FrameLayout> frameLayoutSet = startColors.keySet();

        for (FrameLayout frameLayout : frameLayoutSet) {
            int color = startColors.get(frameLayout);

            if (Color.red(color) != Color.green(color) || Color.red(color) != Color.blue(color)) {
                frameLayout.setBackgroundColor(Color.argb(
                        255,
                        Math.abs(Color.red(color) - progress),
                        Math.abs(Color.green(color) - progress),
                        Math.abs(Color.blue(color) - progress)
                ));
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
