package com.exmaple.android.roomdatabaseexample2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowUsersFragment extends Fragment {
    TextView showUsers;
    public ShowUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_users, container, false);
        showUsers = view.findViewById(R.id.show_users);

        List<User> users = MainActivity.myDataBase.getDao().getUsers();
        for (User user : users) {
            String id = Integer.toString(user.getId());
            String name = user.getName();
            showUsers.append(id +"\n");
            showUsers.append(name +"\n\n");
        }

        return view;
    }
}
