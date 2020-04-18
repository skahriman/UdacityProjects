package com.exmaple.android.roomdatabaseexample2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {
    EditText userId, userName;
    Button addUser;

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        userId = view.findViewById(R.id.et_userId);
        userName = view.findViewById(R.id.et_userName);
        addUser = view.findViewById(R.id.btn_addUser);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(userId.getText().toString());
                String name = userName.getText().toString();

                User user = new User(id, name);
                MainActivity.myDataBase.getDao().add(user);
                Toast.makeText(getActivity(), "successfully added a user", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
