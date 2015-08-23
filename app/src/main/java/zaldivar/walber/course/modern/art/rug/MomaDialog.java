package zaldivar.walber.course.modern.art.rug;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.net.URI;

/**
 * Created by wyro on 23/08/15.
 */
public class MomaDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage("Inspired by work of the artist Sadie Benning\n\nClick below to learn more!")
                .setCancelable(false)

                        // Visit MOMA button
                .setPositiveButton("Visit MOMA",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://www.moma.org/collection/works/187158"));
                                getActivity().startActivity(intent);
                            }
                        })

                        // Not Now button
                .setNegativeButton("Not Now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })

                .create();
    }
}
