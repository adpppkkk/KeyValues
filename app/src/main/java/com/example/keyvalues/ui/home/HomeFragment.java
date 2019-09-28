package com.example.keyvalues.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public View Textview;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                int defaultValue = getResources().getInteger(R.integer.default_saved_key_pressed);
                int timePressed = sharedPref.getInt(getString(R.string.saved_button_press_count_key),defaultValue);

                int newTimePressed = timePressed +1;

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.saved_button_press_count_key), newTimePressed);
                editor.commit();

                textView.setText("Button has been pressed "+ Integer.toString(newTimePressed) + " times!");

            }
        });





        return root;
    }
}