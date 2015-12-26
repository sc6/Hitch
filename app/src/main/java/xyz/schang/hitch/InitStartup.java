package xyz.schang.hitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;

//Code in this class will run only once.
public class InitStartup extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}
