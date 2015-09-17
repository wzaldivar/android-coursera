package zaldivar.walber.course.modern.art.rug;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RugFragment extends Fragment {
    private ArrayList<Float> columnWeights;
    private ArrayList<Float> rowWeights;
    private ArrayList<RGBInformation> rgbColors;
    private Map<FrameLayout, RGBInformation> initialColors;
    private View fragment;

    public RugFragment() {
        fragment = null;

        // Set 5 layout weights in columnWeights
        columnWeights = new ArrayList<Float>();
        columnWeights.add(10f);
        columnWeights.add(15f);
        columnWeights.add(20f);
        columnWeights.add(25f);
        columnWeights.add(30f);

        // rowWeights values are the same
        rowWeights = (ArrayList<Float>) columnWeights.clone();

        // set colors
        rgbColors = new ArrayList<RGBInformation>();

        rgbColors.add(RGBInformation.getRandomWhiteOrGray()); // at least one color is white / gray
        for (int i = 0; i < 4; i++) {
            rgbColors.add(RGBInformation.getRandomNonWhiteOrGrayOrBlack()); // 4 other colors
        }

        // shuffle all
        Collections.shuffle(columnWeights);
        Collections.shuffle(rowWeights);
        Collections.shuffle(rgbColors);

        initialColors = new HashMap<FrameLayout, RGBInformation>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // keep the fragment
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (fragment == null) {
            // first time
            // fill layout dynamically
            fragment = inflater.inflate(R.layout.fragment_rug, container, false);

            LinearLayout rugLayout = (LinearLayout) fragment.findViewById(R.id.rugLayout);
            Context context = fragment.getContext();

            // dynamically add rows
            for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
                LinearLayout row = new LinearLayout(context);
                row.setOrientation(LinearLayout.HORIZONTAL);

                rugLayout.addView(row);
                LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(row.getLayoutParams());
                rowLayoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                rowLayoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                rowLayoutParams.weight = rowWeights.get(rowIndex); // random weight
                row.setLayoutParams(rowLayoutParams);

                // dynamically add frames
                for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                    FrameLayout frame = new FrameLayout(context);

                    row.addView(frame);
                    LinearLayout.LayoutParams columnLayoutParams = new LinearLayout.LayoutParams(frame.getLayoutParams());
                    columnLayoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    columnLayoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
                    columnLayoutParams.weight = columnWeights.get(columnIndex); // random weight
                    columnLayoutParams.setMargins(4, 4, 4, 4);
                    frame.setLayoutParams(columnLayoutParams);

                    // random color
                    int colorIndex = (columnIndex + rowIndex) % 5; //diagonal top-right to bottom-left
                    colorIndex = (colorIndex + 3 * rowIndex) % 5;  //change to diagonal top-left to bottom-right
                    frame.setBackgroundColor(rgbColors.get(colorIndex).getRGBColor());

                    if (!rgbColors.get(colorIndex).isGray()) {
                        // if color is white / gray isn't changed
                        initialColors.put(frame, rgbColors.get(colorIndex));
                    }
                }

                // get color bar
                SeekBar colorBar = (SeekBar) fragment.findViewById(R.id.colorBar);
                // set listener for color bar
                colorBar.setOnSeekBarChangeListener(new OnColorSeekBarChangeListener(initialColors));
            }
        } else {
            // don't recreate the layout again
            ViewGroup parent = (ViewGroup) fragment.getParent();
            if (parent != null) {
                parent.removeView(fragment);
            }
        }

        return fragment;
    }
}
