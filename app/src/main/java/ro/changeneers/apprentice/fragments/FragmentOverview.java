package ro.changeneers.apprentice.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ro.changeneers.apprentice.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FragmentOverview extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentOverview";

    private TextView tvOverviewTitle;
    private TextView tvOverviewText1;
    private TextView tvOverviewText2;
    private ImageView imageViewOvweview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_layout,container,false);

        tvOverviewTitle = view.findViewById(R.id.TextViewOverviewTitle);
        tvOverviewText1 = view.findViewById(R.id.TextViewOverviewText1);
        tvOverviewText2 = view.findViewById(R.id.TextViewOverviewText2);
        imageViewOvweview = view.findViewById(R.id.java_overview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvOverviewText1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            tvOverviewText2.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        return view;
    }
}
