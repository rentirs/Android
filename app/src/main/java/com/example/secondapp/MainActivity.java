package com.example.secondapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Список пользователей");
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new UserListFragment(), "main_fragment").commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = fragmentManager.findFragmentByTag("main_fragment");
        if(currentFragment != null && currentFragment.isVisible()) {
            super.onBackPressed();
        } else {
            Fragment fragment = new UserListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
        }
    }

    public static void changeFragment(View view, User user) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}
