package ro.changeneers.apprentice.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.activities.JourneyActivity;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FragmentStartJourney extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentStartJourney";

    private Button startJourneyBtn;
    private TextView titlu;
    private TextView descriere;
    //private TextView mesaj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_journey_layout,container,false);

        startJourneyBtn = view.findViewById(R.id.ButtonStartJourney);
        titlu = view.findViewById(R.id.titlu_start);
        descriere = view.findViewById(R.id.desc_start);
        //mesaj = view.findViewById(R.id.mesaj_start);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            descriere.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        startJourneyBtn.setOnClickListener(new View.OnClickListener() {

            //boolean visible;

            @Override
            public void onClick(View v) {

                /*TransitionManager.beginDelayedTransition(container);
                visible = !visible;
                mesaj.setVisibility(visible ? View.VISIBLE : View.GONE);*/

                        Intent intent = new Intent(getActivity(),JourneyActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                Toast.makeText(getActivity(), "MULTĂ BAFTĂ !!!", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

}
