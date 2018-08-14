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

public class FragmentStres extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentStres";

    private TextView tvStresTitle;
    private TextView tvStresText1;
    private TextView tvStresText2;
    private TextView tvStresText3;
    private ImageView stresImv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stres_layout,container,false);

        tvStresTitle = view.findViewById(R.id.TextViewStresTitle);
        tvStresText1 = view.findViewById(R.id.TextViewStresText1);
        tvStresText2 = view.findViewById(R.id.TextViewStresText2);
        tvStresText3 = view.findViewById(R.id.TextViewStresText3);
        stresImv = view.findViewById(R.id.img_stress);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvStresText1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            tvStresText2.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        return view;
    }
}
