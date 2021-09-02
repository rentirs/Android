package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {
    private User user;
    private TextView selectItem;
    private Button deleteBtn;
    private Button editBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_user_page, container, false);
        this.selectItem = view.findViewById(R.id.selectItem);
        this.deleteBtn = view.findViewById(R.id.delete);
        Users users = new Users();
        deleteBtn.setOnClickListener(v -> {
            users.deleteUser(user.getUuid());
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        this.editBtn = view.findViewById(R.id.edit);
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddUserActivity.class);
            intent.putExtra("msg", "edit");
            intent.putExtra("user", user);
            startActivity(intent);
        });
        return view;
    }

    // Called when configuration change.
    // For example: User rotate the Phone,
    //  Android will create new Fragment (UserFragment) object via default Constructor
    //  so this.user will be null.
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Bundle dataBundle = this.userToBundle(this.user);
        outState.putAll(dataBundle);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(this.user == null) this.user = this.bundleToUser(savedInstanceState);
        this.showInGUI(this.user);
    }

    private void showInGUI(User user) {
        this.selectItem.setText(user.getName() + " " + user.getLastName() + "\n" + "Телефон: " + user.getPhone());
    }

    private Bundle userToBundle (User user) {
        Bundle bundle = new Bundle();
        bundle.putString("name",user.getName());
        bundle.putString("lastName", user.getLastName());
        bundle.putString("phone", user.getPhone());
        return bundle;
    }

    private User bundleToUser(Bundle savedInstanceState) {
        String name = savedInstanceState.getString("name");
        String lastName = savedInstanceState.getString("lastName");
        String phone = savedInstanceState.getString("phone");
        user.setName(name);
        user.setLastName(lastName);
        user.setPhone(phone);
        return new User();
    }
}
