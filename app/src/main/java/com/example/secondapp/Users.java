package com.example.secondapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class Users {
    private final String HTTP = "152.70.168.210";

    public void addUser(User user) {
        String host = "http://" + HTTP + "/handlerAddUser.php?" +
                "name=" + user.getName() +
                "&lastname=" + user.getLastName() +
                "&phone=" + user.getPhone() +
                "&uuid=" + user.getUuid().toString();
        MyRunnable runnable = new MyRunnable(host, "addUser");
        Thread t3 = new Thread(runnable);
        t3.start();
    }

    public void updateUser(User user) {
        String host = "http://" + HTTP + "/handlerUpdateUser.php?" +
                "name=" + user.getName() +
                "&lastname=" + user.getLastName() +
                "&phone=" + user.getPhone() +
                "&uuid=" + user.getUuid().toString();
        MyRunnable runnable = new MyRunnable(host, "updateUser");
        Thread t4 = new Thread(runnable);
        t4.start();
    }

    public void deleteUser(UUID uuid) {
        System.out.println(uuid.toString());
        String host = "http://" + HTTP + "/handlerDeleteUser.php?" + "uuid=" + uuid.toString();
        MyRunnable runnable = new MyRunnable(host, "deleteUser");
        Thread t2 = new Thread(runnable);
        t2.start();
    }

    public ArrayList<User> getUserLists() {
        String host = "http://" + HTTP + "/handlerGetUsers.php";
        MyRunnable runnable = new MyRunnable(host, "getUserLists");
        Thread t1 = new Thread(runnable);
        t1.start();
        while (t1.isAlive()) {}
        return runnable.getUserList();
    }

    public static class MyRunnable implements Runnable {
        private final String host;
        private final String msgDoIt;
        private final ArrayList<User> userList = new ArrayList<>();
        public MyRunnable(String host, String msgDoIt) {
            this.host = host;
            this.msgDoIt = msgDoIt;
        }
        public synchronized ArrayList<User> getUserList() {
            return userList;
        }
        @Override
        public void run() {
            try {
                URL url = new URL(host);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                int i;
                StringBuilder result = new StringBuilder();
                while ((i = reader.read()) != -1) {
                    result.append((char) i);
                }
                if(msgDoIt.equals("getUserLists")) {
                    JSONArray jsonArray = new JSONArray(result.toString());
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        UUID uuid = UUID.fromString(jsonObject.getString("uuid"));
                        User user = new User(uuid);
                        user.setName(jsonObject.getString("name"));
                        user.setLastName(jsonObject.getString("lastname"));
                        user.setPhone(jsonObject.getString("phone"));
                        userList.add(user);
                    }
                }
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
