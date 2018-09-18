package com.loancare.lakeview.Fingerprint;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import com.loancare.lakeview.WebActivity;


@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintAuthenticationHandler extends FingerprintManager.AuthenticationCallback
{


    private Context context;


    // Constructor
    public FingerprintAuthenticationHandler(Context mContext)
    {
        context = mContext;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject)
    {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString)
    {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString)
    {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed()
    {
        this.update("Fingerprint Authentication failed.", false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result)
    {
        this.update("Fingerprint Authentication succeeded.", true);
    }


    public void update(String e, Boolean success)
    {
        //TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText1);
        //textView.setText(e);
      //  Toast.makeText(((Activity)context).getApplicationContext(),e,Toast.LENGTH_SHORT).show();
        Toast.makeText(context.getApplicationContext(),e,Toast.LENGTH_SHORT).show();
        if(success)
        {
            //textView.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            Intent i = new Intent(context, WebActivity.class);
            context.startActivity(i);
        }
    }


}