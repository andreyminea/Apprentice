package ro.changeneers.apprentice;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentStres extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentOverview";

    private TextView tvStres;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stres_layout,container,false);
        tvStres = view.findViewById(R.id.TextViewStres);
        return view;
    }
}
