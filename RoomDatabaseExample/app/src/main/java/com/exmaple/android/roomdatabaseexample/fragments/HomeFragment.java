package com.exmaple.android.roomdatabaseexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    Button btnAddUser, btnReadUser, btnDeleteUser, btnUpdateUser;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnAddUser = view.findViewById(R.id.btn_add_user);
        btnReadUser = view.findViewById(R.id.btn_view_user);
        btnDeleteUser = view.findViewById(R.id.btn_delete_user);
        btnUpdateUser = view.findViewById(R.id.btn_update_user);
        btnUpdateUser.setOnClickListener(this);
        btnDeleteUser.setOnClickListener(this);
        btnReadUser.setOnClickListener(this);
        btnAddUser.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_user:
                MainActivity.fragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new AddUserFragment())
                        .commit();
                break;
            case R.id.btn_view_user:
                MainActivity.fragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new ReadUserFragment())
                        .commit();
                break;
            case R.id.btn_delete_user:
                MainActivity.fragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new DeleteUserFragment())
                        .commit();
                break;
            case R.id.btn_update_user:
                MainActivity.fragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new UpdateFragment())
                        .commit();
                break;
        }
    }
}
