package com.example.lllll.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.lllll.R;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<EditText> arrayList;
    private EditText editText;
    private LinearLayout layout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button button = root.findViewById(R.id.button);
        layout = root.findViewById(R.id.layout);
        arrayList = new ArrayList<>();

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);


        //When the app reopens, setting the value in all the EditTexts.
        int i = 0;
        while(true)
        {
            String key = Integer.toString(i);
            if (sharedPref.contains(key)) {
                editText = new EditText(getActivity());
                layout.addView(editText);
                arrayList.add(editText);
                editText.setText(sharedPref.getString(key, "DEFAULT"));
                i++;
            }
            else
                break;

        }

        //When the button is clicked, adding new EditText
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = new EditText(getActivity());
                layout.addView(editText);
                arrayList.add(editText);
            }
        });
        return root;
    }


    //Saving all the data of the EditText when the Fragement is destroyed.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        //Saving all the data in sharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        for(int i = 0; i < arrayList.size(); i++)
        {
            if(!arrayList.get(i).getText().toString().isEmpty()) {
                editor.putString(""+i, arrayList.get(i).getText().toString());
            }
            else
                editor.putString(""+i, "");
        }
        editor.apply();
    }
}
