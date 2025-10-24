package com.example.androiduitesting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Objects;

public class ShowActivityDialogFragment extends DialogFragment {
    interface ShowActivityDialogListener {
        //void showCity(City city, String title, String year);
    }
    private ShowActivityDialogListener listener;
    private Button backButton;

    public static ShowActivityDialogFragment newInstance(String cityName){
        Bundle args = new Bundle();
        args.putSerializable("City", cityName);

        ShowActivityDialogFragment fragment = new ShowActivityDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ShowActivityDialogListener){
            listener = (ShowActivityDialogListener) context;
        }
        else {
            throw new RuntimeException("Implement listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.show_activity_fragment, null);
        TextView textToReplace = view.findViewById(R.id.city_text);
        Button backButton = view.findViewById(R.id.button_back);

        String tag = getTag();
        Bundle bundle = getArguments();
        String cityName;

        if (Objects.equals(tag, "City Details") && bundle != null){
            cityName = (String) bundle.getSerializable("City");
            assert cityName != null;
            textToReplace.setText(cityName);
        }
        else {
            cityName = null;}

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("City Details")
                /*
                .setPositiveButton("Continue", (dialog, which) -> {
                    String title = textToReplace.getText().toString();
                    String year = editMovieYear.getText().toString();
                    if (Objects.equals(tag, "City Details")) {
                        listener.updateCity(city, title, year);
                    } else {
                        listener.addCity(new City(title, year));
                    }
                })
                */
                .create();
    }
}