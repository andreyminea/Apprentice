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

public class FragmentBani extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentBani";

    private TextView tvBaniTitle;
    private TextView tvBaniText;
    private ImageView imageViewBani;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bani_layout,container,false);

        tvBaniTitle = view.findViewById(R.id.TextViewBaniTitle);
        tvBaniText = view.findViewById(R.id.TextViewBaniText);
        imageViewBani = view.findViewById(R.id.money_fragment);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvBaniText.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        return view;
    }
}
