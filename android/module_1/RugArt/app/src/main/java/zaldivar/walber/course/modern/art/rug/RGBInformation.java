package zaldivar.walber.course.modern.art.rug;

import android.graphics.Color;

import java.util.Random;

// custom RGB information
public class RGBInformation {
    private final int red;
    private final int green;
    private final int blue;

    private static final int WHITE_COMPONENTS = Color.red(Color.WHITE);
    private static final int BLACK_COMPONENTS = Color.red(Color.BLACK);
    private static final int LTGRAY_COMPONENTS = Color.red(Color.LTGRAY);
    private static final int DKGRAY_COMPONENTS = Color.red(Color.DKGRAY);
    private static final int NO_TRANSPARENCY = 255;

    public RGBInformation(int color) {
        red = Color.red(color);
        green = Color.green(color);
        blue = Color.blue(color);
    }

    public static RGBInformation getRandomWhiteOrGray() {
        Random random = new Random();
        if (random.nextBoolean()) {
            // white
            return new RGBInformation(Color.WHITE);
        } else {
            // some gray between LTGRAY and DKGRAY
            int gray = random.nextInt(LTGRAY_COMPONENTS - DKGRAY_COMPONENTS + 1) + DKGRAY_COMPONENTS;
            return new RGBInformation(Color.argb(NO_TRANSPARENCY, gray, gray, gray));
        }
    }

    public static RGBInformation getRandomNonWhiteOrGrayOrBlack() {
        Random random = new Random();

        RGBInformation rgbInformation;
        do {
            int randomRed = random.nextInt(256);
            int randomGreen = random.nextInt(256);
            int randomBlue = random.nextInt(256);

            rgbInformation = new RGBInformation(Color.argb(NO_TRANSPARENCY, randomRed, randomGreen, randomBlue));
        } while (rgbInformation.isGray()); // be sure isn't gray

        return rgbInformation;
    }

    public boolean isGray() {
        return red == green && red == blue;
    }

    public boolean isWhite() {
        return isGray() && red == WHITE_COMPONENTS;
    }

    public boolean isBlack() {
        return isGray() && red == BLACK_COMPONENTS;
    }

    public int getRGBColor() {
        return Color.argb(NO_TRANSPARENCY, red, green, blue);
    }

    // get RGBColor changed by a delta
    public int getRGBColor(int delta) {
        delta = Math.abs(delta);
        return Color.argb(NO_TRANSPARENCY, calcDelta(red, delta), calcDelta(green, delta), calcDelta(blue, delta));
    }

    private static int calcDelta(int color, int delta) {
        color = Math.abs(color - delta);

        if (color > 255) {
            color = color % 255;
        }

        return color;
    }
}
