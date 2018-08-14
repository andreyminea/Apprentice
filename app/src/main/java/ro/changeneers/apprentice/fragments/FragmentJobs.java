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

public class FragmentJobs extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentJobs";

    private TextView tvJobsTitle;
    private TextView tvJobsText1;
    private TextView tvJobsText2;
    private TextView tvJobsText3;
    private TextView tvJobsText4;
    private ImageView jobImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs_layout,container,false);

        tvJobsTitle = view.findViewById(R.id.TextViewJobsTitle);
        tvJobsText1 = view.findViewById(R.id.TextViewJobsText1);
        tvJobsText2 = view.findViewById(R.id.TextViewJobsText2);
        tvJobsText3 = view.findViewById(R.id.TextViewJobsText3);
        tvJobsText4 = view.findViewById(R.id.TextViewJobsText4);
        jobImg = view.findViewById(R.id.img_job);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvJobsText1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            tvJobsText2.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            tvJobsText4.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        return view;
    }
}
