package xyz.schang.hitch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RequestFragment extends Fragment {
    public static final String REQUEST_FRAGMENT = "REQUEST_FRAGMENT";

    private static RequestFragment rf;

    public static RequestFragment newInstance() {
        if(rf == null) {
            RequestFragment fragment = new RequestFragment();
            rf = fragment;
        }

        return rf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text_sample);
        textView.setText("This is the requests tab");
        return view;
    }
}
