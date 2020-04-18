package com.exmaple.android.roomdatabaseexample2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    Button btnAddUser, btnDeleteUser, btnUpdateUser, btnViewUsers;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnAddUser = view.findViewById(R.id.btn_add_user);
        btnDeleteUser = view.findViewById(R.id.btn_deleteUser);
        btnUpdateUser = view.findViewById(R.id.btn_updateUser);
        btnViewUsers = view.findViewById(R.id.btn_allUsers);

        btnAddUser.setOnClickListener(this);
        btnViewUsers.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_user :
                MainActivity.fragmentManager.
                        beginTransaction()
                        .replace(R.id.container_fragment, new AddUserFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_allUsers :
                MainActivity.fragmentManager.
                        beginTransaction()
                        .replace(R.id.container_fragment, new ShowUsersFragment())
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }
}
