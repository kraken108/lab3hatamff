package p_jakdan_hjalmo.aktakurvan;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        
        checkGoogleApiAvailability();

    }

    private void checkGoogleApiAvailability(){
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.getApplicationContext());

        if(resultCode == 0){
            Log.i("info","WTF DET FUNKAR JU M8");
        }else if(resultCode == 1 || resultCode == 2 || resultCode == 3){
            Log.i("Return code ", "" + resultCode);
            Dialog d = GoogleApiAvailability.getInstance().getErrorDialog(this,resultCode,resultCode);
            d.show();
        }
    }
}
