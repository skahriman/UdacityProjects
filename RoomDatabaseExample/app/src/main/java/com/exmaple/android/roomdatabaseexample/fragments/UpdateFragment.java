package com.exmaple.android.roomdatabaseexample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exmaple.android.roomdatabaseexample.MainActivity;
import com.exmaple.android.roomdatabaseexample.R;
import com.exmaple.android.roomdatabaseexample.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {
    private EditText userId, userName;
    private Button btnUpdateUser;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        userId = view.findViewById(R.id.et_update_use_id);
        userName = view.findViewById(R.id.et_update_user_name);
        btnUpdateUser = view.findViewById(R.id.update_user);

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(userId.getText().toString());
                String name = userName.getText().toString();
                User user = new User(id, name);
                MainActivity.myAppDatabase.getDao().updateUser(user);
                Toast.makeText(getActivity(), "deleted user successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
