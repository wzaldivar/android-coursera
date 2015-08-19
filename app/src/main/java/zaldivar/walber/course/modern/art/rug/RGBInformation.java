package zaldivar.walber.course.modern.art.rug;

import android.graphics.Color;

/**
 * Created by wzaldivar on 19/08/15.
 */
public class RGBInformation {
    private final int red;
    private final int green;
    private final int blue;

    public RGBInformation(int color) {
        red = Color.red(color);
        green = Color.green(color);
        blue = Color.blue(color);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public boolean isGray() {
        return red == green && red == blue;
    }

    public boolean isWhite() {
        return isGray() && red == 0xff;
    }

    public boolean isBlack() {
        return isGray() && red == 0;
    }
}
