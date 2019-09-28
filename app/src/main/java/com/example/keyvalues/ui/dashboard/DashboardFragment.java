package com.example.keyvalues.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.keyvalues.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    public View Textview;
    public TextView Time,ClickCount;
    private int counter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        final TextView Time = root.findViewById(R.id.textView);
        final TextView ClickCount = root.findViewById(R.id.counter);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }


        });


        Button b_start = root.findViewById(R.id.Start);
        Button b_trigger = root.findViewById(R.id.Trigger);

        //b_trigger.setEnabled(false);

/*        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //b_start.setVisibility(View.GONE);
                //b_trigger.setEnabled(true);
                counter = 0;


                //trigger start
                b_trigger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter++;
                        ClickCount.setText(counter + "clicks!");
                    }
                });

            }
        });

*/



        return root;
    }
}