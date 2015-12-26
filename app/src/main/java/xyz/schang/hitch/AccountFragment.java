package xyz.schang.hitch;


import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;


public class AccountFragment extends Fragment {
    public static final String ACCOUNT_FRAGMENT = "ACCOUNT_FRAGMENT";

    private static AccountFragment af;
    private ListView list_accountOptions;

    public static AccountFragment newInstance() {
        if(af == null) {
            AccountFragment fragment = new AccountFragment();
            af = fragment;
        }
        return af;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ArrayList<String> array_accountOptions = new ArrayList<>();
        array_accountOptions.add("Log out (" + HitchActivity.user.getUsername() + ")");
        array_accountOptions.add("View public profile");

        list_accountOptions = (ListView) view.findViewById(R.id.list_accountOptions);
        list_accountOptions.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, array_accountOptions));


        list_accountOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setText("Logging out...");
                    ((TextView) view).setTextColor(Color.parseColor("#FF0000"));
                    HitchActivity.user.logOutInBackground();
                    Intent i = new Intent(getContext(), LoginActivity.class); // Your list's Intent
                    startActivity(i);
                }
            }
        });


        return view;
    }
}
