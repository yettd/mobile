package com.example.mgp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class RestartConfirmDialogFragment extends DialogFragment {
    public static boolean isShown=false;
    @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        isShown=true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confrim QUIT?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ResourceManager.Instance.list.clear();
//          //Example of touch on screen in the main game to trigger back to Main menu
                StateManager.Instance.ChangeState("Mainmenu");

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
