package com.agroapp.mobile.agroapp.Data;

import android.content.Context;

import com.agroapp.mobile.agroapp.Entities.User;
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

public class UserDAO {
    private static UserDAO _Instancia;
    private String urlCnn = UrlConnection.URL_CONECCTION;
    private Gson gson;

    public static UserDAO Instancia() {
        if (_Instancia == null) {
            _Instancia = new UserDAO();
        }
        return _Instancia;
    }


}
