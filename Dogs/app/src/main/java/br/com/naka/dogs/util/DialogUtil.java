package br.com.naka.dogs.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;

import br.com.naka.dogs.R;

public class DialogUtil {

    public static Dialog getDialog(Context context ) {
        final Dialog dialog = new Dialog( context , R.style.NewDialog);
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.dialog );

        ProgressBar dprogress = (ProgressBar) dialog.findViewById( R.id.dialog_progress );

        return dialog;
    }

    public static Dialog getDialogProgress(Context context ) {
        final Dialog dialog = new Dialog( context, R.style.NewDialog );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setContentView( R.layout.dialog );
        dialog.setCanceledOnTouchOutside( false );
        return dialog;
    }

}
