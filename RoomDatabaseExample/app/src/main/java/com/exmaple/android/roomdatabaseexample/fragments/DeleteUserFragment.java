package com.exmaple.android.roomdatabaseexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteUserFragment extends Fragment {

    private EditText userId;
    private Button deleteButton;

    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);
        userId = view.findViewById(R.id.et_delete_user);
        deleteButton = view.findViewById(R.id.btn_delete_user);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(userId.getText().toString());
                User user = new User();
                user.setId(id);
                MainActivity.myAppDatabase.mMyDao().deleteUser(user);
                Toast.makeText(getActivity(), "User successfully deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
