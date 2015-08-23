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

public class OnColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    private final Map<FrameLayout, RGBInformation> startColors = new HashMap<FrameLayout, RGBInformation>();

    public OnColorSeekBarChangeListener(ArrayList<FrameLayout> frameLayouts) {
        for (FrameLayout frameLayout : frameLayouts) {
            int color;
            Drawable background = frameLayout.getBackground();
            if (background instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) background;
                color = colorDrawable.getColor();

                RGBInformation rgbInformation = new RGBInformation(color);
                if (!rgbInformation.isGray()) {
                    startColors.put(frameLayout, rgbInformation);
                }
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Set<FrameLayout> frameLayoutSet = startColors.keySet();

        for (FrameLayout frameLayout : frameLayoutSet) {
            RGBInformation rgbInformation = startColors.get(frameLayout);

//            frameLayout.setBackgroundColor(rgbInformation.getRGBColor(progress));
            frameLayout.setBackgroundColor(RGBInformation.randomNonWhiteOrGrayOrBlack().getRGBColor());
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
