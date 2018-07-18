package ro.changeneers.apprentice.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ro.changeneers.apprentice.R;

public class FragmentBani extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentBani";

    private TextView tvBaniTitle;
    private TextView tvBaniText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bani_layout,container,false);
        tvBaniTitle = view.findViewById(R.id.TextViewBaniTitle);
        tvBaniText = view.findViewById(R.id.TextViewBaniText);
        return view;
    }
}
