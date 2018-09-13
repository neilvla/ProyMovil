package com.agroapp.mobile.agroapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agroapp.mobile.agroapp.ControlRegisterActivity;
import com.agroapp.mobile.agroapp.Entities.User;
import com.agroapp.mobile.agroapp.Entities.UserResult;
import com.agroapp.mobile.agroapp.HomeActivity;
import com.agroapp.mobile.agroapp.R;
import com.agroapp.mobile.agroapp.Utilities.AppController;
import com.agroapp.mobile.agroapp.Utilities.UrlConnection;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        validateUser(view);

        return view;
    }

    private void validateUser(View view) {
        final EditText user = (EditText) view.findViewById(R.id.input_email);
        final EditText pass = (EditText) view.findViewById(R.id.input_password);

        Button btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(user.getText().toString(), pass.getText().toString());
            }
        });
    }

    private void validate(String usu, String pass) {
        Map<String, String> params = new HashMap<>();
        params.put("Email", usu);
        params.put("Password", pass);
        JSONObject jsonParams = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, String.format(UrlConnection.URL_CONECCTION, "authentication/validateuser"),
                jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String rpt = response.toString();
                    if (response.length() > 0) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                        gson = gsonBuilder.create();
                        //User u = gson.fromJson(rpt, User.class);
                        UserResult userResponse = gson.fromJson(rpt, UserResult.class);
                        if (userResponse.getTypeResult() == 0) {
                            if (userResponse.getResult() != null) {
                                /*Intent intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra("User", userResponse.getResult());
                                startActivity(intent);*/
                                getStatusCropControl(userResponse.getResult());
                            }else{
                                Toast.makeText(getActivity(), "Usuario no se encuentra registrado", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("tag", "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
            }
        });
        AppController.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

    private void getStatusCropControl(User user){
        final User u = user;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, String.format(UrlConnection.URL_CONECCTION, "authentication/getstatuscropcontrol/" + user.getId()),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String rpt = response.toString();
                    if (response.length() > 0) {
                        if (Integer.parseInt(response.get("typeResult").toString()) == 0) {
                            Intent intent;
                            if (Integer.parseInt(response.get("result").toString()) != 0) {
                                intent = new Intent(getActivity(), HomeActivity.class);
                            }else{
                                //intent = new Intent(getActivity(), ControlRegisterActivity.class);
                                intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra("User", u);
                            }
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("tag", "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
            }
        });
        AppController.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }
}
