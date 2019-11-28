package pl.krzywyyy.barter.utils.dialogs;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import pl.krzywyyy.barter.R;

public class DiscardChangesDialog {
    public static void show(Context context, DialogFragment fragment) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.discard_changes_title)
                .setMessage(R.string.discard_changes_message)
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    fragment.dismiss();
                })
                .setNegativeButton(R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
