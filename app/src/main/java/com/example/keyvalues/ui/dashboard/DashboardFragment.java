package com.example.keyvalues.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
    private CountDownTimer countdowntimer;
    private long timems = 30000; //30 sec

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        final TextView Time = root.findViewById(R.id.timer);
        final TextView ClickCount = root.findViewById(R.id.counter);
        SharedPreferences Preference = getActivity().getSharedPreferences("SCORE", Context.MODE_PRIVATE);
        final int Bestsocre = Preference.getInt("Bestscore",0);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("The best score is "+ Bestsocre + " clicks!");
            }


        });


        final Button b_start = root.findViewById(R.id.Start);
        final ImageButton b_trigger = root.findViewById(R.id.trigger_button);

        b_trigger.setEnabled(false);
        ClickCount.setText("0 clicks!");
        Time.setText("30 sec");


        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_start.setVisibility(View.GONE);
                b_trigger.setEnabled(true);
                counter = 0;

                countdowntimer = new CountDownTimer(timems,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timems = millisUntilFinished;
                        int seconds = (int) timems/1000;
                        Time.setText(seconds+" sec");

                        //trigger start
                        b_trigger.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                counter++;
                                ClickCount.setText(counter + " clicks!");
                            }
                        });
                    }

                    @Override
                    public void onFinish() {
                        b_trigger.setEnabled(false);
                        /*SharedPreferences Preference = getActivity().getSharedPreferences("SCORE", Context.MODE_PRIVATE);
                        int Bestsocre = Preference.getInt("Bestscore",0);*/

                        if(counter > Bestsocre){
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("SCORE",Context.MODE_PRIVATE).edit();
                            editor.putInt("Bestscore",counter);
                            editor.commit();
                            textView.setText("The best score is "+ counter + " clicks!");
                        }
                        else{
                            textView.setText("The best score is "+ Bestsocre + " clicks!");
                        }
                    }
                }.start();



            }
        });






        return root;
    }



}