package br.com.naka.dogs.util;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Singleton {

    private static Singleton instance;
    private static Dialog mDialog;

    public static Singleton getInstance( ) {
        if ( instance == null ) {
            instance = new Singleton( );
        }
        return instance;
    }

    public void showLoading( Context context ) {

        if ( mDialog == null ) {
            mDialog = DialogUtil.getDialog( context );
        }

        try{
            if ( !mDialog.isShowing( ) ) {
                mDialog.show( );
                mDialog.setCancelable(false);

            }
        }catch (Exception ex){
            mDialog = DialogUtil.getDialog( context );
        }

    }

    public void dismissLoading( ) {
        if (mDialog != null && mDialog.isShowing( ) ) {

            mDialog.dismiss( );
        }
        mDialog = null;
    }

    public static boolean isDeviceOnline(Context context) {
        boolean isConnectionAvail = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnectionAvail;
    }


}
