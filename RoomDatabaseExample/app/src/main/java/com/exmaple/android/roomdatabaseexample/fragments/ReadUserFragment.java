package com.exmaple.android.roomdatabaseexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    TextView mTextView;
    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);

        mTextView = view.findViewById(R.id.tv_display_info);

        List<User> users = MainActivity.myAppDatabase.mMyDao().getUsers();
        for (User user : users) {
            mTextView.append(Integer.toString(user.getId())+"\n");
            mTextView.append(user.getName()+"\n\n");
        }

        return view;

    }
}
