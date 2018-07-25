package ro.changeneers.apprentice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ro.changeneers.apprentice.R;

public class FragmentJobs extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentJobs";

    private TextView tvJobsTitle;
    private TextView tvJobsText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs_layout,container,false);
        tvJobsTitle = view.findViewById(R.id.TextViewJobsTitle);
        tvJobsText = view.findViewById(R.id.TextViewJobsText);
        return view;
    }
}
