package part2.android.course.zaldivar.walber.dailyselfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wyro on 04/10/15.
 */
public class NotificationLauncher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, DailySelfieActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
        Notification.Builder builder = new Notification.Builder(context).
                setContentTitle(context.getString(R.string.app_name)).
                setContentText(context.getString(R.string.selfie_time)).
                setSmallIcon(android.R.drawable.ic_menu_camera).
                setContentIntent(pi).
                setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }
}
