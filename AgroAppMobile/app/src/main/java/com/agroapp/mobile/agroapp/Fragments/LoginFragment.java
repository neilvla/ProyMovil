package com.agroapp.mobile.agroapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.agroapp.mobile.agroapp.Data.UserService;
import com.agroapp.mobile.agroapp.Entities.Result;
import com.agroapp.mobile.agroapp.Entities.User;
import com.agroapp.mobile.agroapp.HomeActivity;
import com.agroapp.mobile.agroapp.R;
import com.agroapp.mobile.agroapp.Utilities.APIUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private Gson gson;
    private UserService userService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        userService = APIUtils.getUserService();
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
                //getId(5);
            }
        });
    }

    private void validate(String usu, String pass) {
        User usuario = new User();
        usuario.setEmail(usu);
        usuario.setPassword(pass);
        Call<Result<User>> call = userService.validate(usuario);
        call.enqueue(new Callback<Result<User>>() {
            @Override
            public void onResponse(Call<Result<User>> call, Response<Result<User>> response) {
                if(response.isSuccessful()){
                    User u = response.body().getResult();
                    getStatusCropControl(u);
                }
            }

            @Override
            public void onFailure(Call<Result<User>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void getStatusCropControl(User u){
        Call<Result<Short>> call = userService.get_StatusCropControl(u.getId());
        final User user = u;
        call.enqueue(new Callback<Result<Short>>() {
            @Override
            public void onResponse(Call<Result<Short>> call, Response<Result<Short>> response) {
                if(response.isSuccessful()){
                    Intent intent;
                    if (response.body().getResult() != 0){
                        intent = new Intent(getActivity(), HomeActivity.class);
                    }else{
                        intent = new Intent(getActivity(), HomeActivity.class);
                        intent.putExtra("User", user);
                    }
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Result<Short>> call, Throwable t) {

            }
        });
    }




    /*private void validate(String usu, String pass) {
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
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra("User", userResponse.getResult());
                                startActivity(intent);
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
    }*/
}
