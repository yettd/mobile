package com.example.mgp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GetNameDialog extends DialogFragment {
    private ArrayList<ArrayList<String>> OverAllScore = new ArrayList<ArrayList<String>>();

    public static boolean isShown=false;
    EditText input;
    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        OverAllScore=ResourceManager.Instance.getOAS();
        isShown=true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        input = new EditText(getActivity());
        builder.setView(input);
        builder.setMessage("Top 3 Please Enter name").setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String name=input.getText().toString();

                OverAllScore.get(ResourceManager.Instance.Place).set(0,name);
                Gson g=new Gson();
                String t=g.toJson(OverAllScore);
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetString("OAS",t);
                GameSystem.Instance.SaveEditEnd();
                ResourceManager.Instance.SetOAS();
                isShown=false;
            }
        });
        return builder.create();
    }

}
