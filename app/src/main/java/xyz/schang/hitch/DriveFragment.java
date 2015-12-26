package xyz.schang.hitch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DriveFragment extends Fragment {
    public static final String DRIVE_FRAGMENT = "DRIVE_FRAGMENT";

    private static DriveFragment df;

    public static DriveFragment newInstance() {
        if(df == null) {
            DriveFragment fragment = new DriveFragment();
            df = fragment;
        }

        return df;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drive, container, false);
        return view;
    }
}
