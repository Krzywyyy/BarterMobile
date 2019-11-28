package pl.krzywyyy.barter.utils.dialogs;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import pl.krzywyyy.barter.R;

public class DeleteDialog {

    public static void show(Context context, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.delete_title)
                .setMessage(R.string.delete_message)
                .setPositiveButton(
                        R.string.delete, listener)
                .setNegativeButton(R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
