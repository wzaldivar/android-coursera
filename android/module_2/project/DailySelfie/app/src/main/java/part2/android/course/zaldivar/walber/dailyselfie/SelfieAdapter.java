package part2.android.course.zaldivar.walber.dailyselfie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by wyro on 04/10/15.
 */
public class SelfieAdapter extends BaseAdapter implements View.OnTouchListener {
    private ArrayList<SelfieInfo> list = new ArrayList<SelfieInfo>();
    private LayoutInflater inflater;
    private GestureDetector gestureDetector;
    private View clickedVied;

    public SelfieAdapter(final Context context) {
        inflater = LayoutInflater.from(context);

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                try {
                    SelfieInfo info = (SelfieInfo) clickedVied.getTag();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse("file://" + info.getAbsPath()), "image/*");
                    context.startActivity(intent);
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            }
        });

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        SelfieInfo current = list.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.selfie_view, parent, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.selfie_thumbnail);
        imageView.setImageBitmap(current.getThumbnail());

        TextView textView = (TextView) view.findViewById(R.id.selfie_title);
        textView.setText(current.getTitle());

        view.setTag(current);
        view.setOnTouchListener(this);

        return view;
    }

    public void add(SelfieInfo info) {
        list.add(0, info);
        notifyDataSetChanged();
    }

    public void fill(File dailyDir) {
        File[] selfiesFiles = dailyDir.listFiles();
        if (selfiesFiles != null) {
            for (File selfieFile : selfiesFiles) {
                if (selfieFile.getName().endsWith(".jpg")) {
                    list.add(new SelfieInfo(selfieFile));
                }
            }
        }
        Collections.sort(list);
        Collections.reverse(list);
        notifyDataSetChanged();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        clickedVied = v;
        return gestureDetector.onTouchEvent(event);
    }
}
