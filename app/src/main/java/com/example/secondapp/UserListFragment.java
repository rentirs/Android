package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UserListFragment extends Fragment {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button add;
    ArrayList<User> userList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddUserActivity.class);
            intent.putExtra("msg", "add");
            startActivity(intent);
        });
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    private void recyclerViewInit() {
        Users users = new Users();
        userList = users.getUserLists();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewInit();
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView itemTextView;
        private User mUser;

        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }

        public void bind(String userName, User user) {
            mUser = user;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
/*
            Intent intent = new Intent(getContext(), UserPagerActivity.class);
            startActivity(intent);
*/
            MainActivity.changeFragment(view, mUser);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {
        final ArrayList<User> users;

        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            String userString = user.getName() + "\n" + user.getLastName();
            userHolder.bind(userString, user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
