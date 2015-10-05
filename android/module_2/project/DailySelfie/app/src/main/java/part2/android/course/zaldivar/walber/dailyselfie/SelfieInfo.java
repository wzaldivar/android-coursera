package part2.android.course.zaldivar.walber.dailyselfie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import java.io.File;

/**
 * Created by wyro on 04/10/15.
 */
public class SelfieInfo implements Comparable<SelfieInfo> {
    private Bitmap thumbnail;
    private String title;
    private String absPath;

    SelfieInfo(File file) {
        title = file.getName().replace(".jpg", "");
        absPath = file.getAbsolutePath();
        Bitmap bitmap = BitmapFactory.decodeFile(absPath);
        thumbnail = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getScaledWidth(200), bitmap.getScaledHeight(200));
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getAbsPath() {
        return absPath;
    }

    @Override
    public int compareTo(SelfieInfo another) {
        return title.compareTo(another.title);
    }
}
