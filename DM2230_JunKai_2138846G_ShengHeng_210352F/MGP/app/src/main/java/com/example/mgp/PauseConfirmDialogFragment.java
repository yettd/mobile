package com.example.mgp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class PauseConfirmDialogFragment extends DialogFragment {
    public static boolean isShown=false;
    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        isShown=true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confrim Pause?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                isShown=false;
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                isShown=false;
            }
        });
        return builder.create();
    }

}
