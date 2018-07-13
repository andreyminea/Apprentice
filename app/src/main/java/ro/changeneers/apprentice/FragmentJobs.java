package ro.changeneers.apprentice;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentJobs extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentJobs";

    private TextView tvJobs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs_layout,container,false);
        tvJobs = view.findViewById(R.id.TextViewJobs);
        return view;
    }
}
