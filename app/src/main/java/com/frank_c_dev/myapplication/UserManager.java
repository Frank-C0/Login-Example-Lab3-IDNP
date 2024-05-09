package com.frank_c_dev.myapplication;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserManager {
    private static final String FILE_NAME = "users.json";

    private Context context;

    public UserManager(Context context) {
        this.context = context;
    }

    public void registerUser(User user) {
        JSONObject userObject = new JSONObject();
        try {
            userObject.put("firstName", user.getFirstName());
            userObject.put("lastName", user.getLastName());
            userObject.put("email", user.getEmail());
            userObject.put("phone", user.getPhone());
            userObject.put("password", user.getPassword());

            String userData = userObject.toString();
            FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE | Context.MODE_APPEND);
            outputStream.write(userData.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loginUser(String email, String password) {
        try {
            FileInputStream inputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                JSONObject userObject = new JSONObject(line);
                String storedEmail = userObject.getString("email");
                String storedPassword = userObject.getString("password");
                Log.d("JSON data", "s: "+storedEmail+" "+storedPassword+" i: "+email+" "+password);
                if (email.equals(storedEmail) && password.equals(storedPassword)) {
                    return true;
                }
            }
            inputStream.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserFullName(String email) {
        try {
            FileInputStream inputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                JSONObject userObject = new JSONObject(line);
                String storedEmail = userObject.getString("email");
                if (email.equals(storedEmail)) {
                    String firstName = userObject.getString("firstName");
                    String lastName = userObject.getString("lastName");
                    return firstName + " " + lastName;
                }
            }
            inputStream.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
