package com.agroapp.mobile.agroapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.agroapp.mobile.agroapp.Adapters.StageAdapter;
import com.agroapp.mobile.agroapp.Entities.Stage;
import com.agroapp.mobile.agroapp.Entities.StageResult;
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

import java.util.ArrayList;
import java.util.List;

import pl.hypeapp.materialtimelineview.MaterialTimelineView;

public class HomeActivity extends AppCompatActivity {

    //private List<Stage> stageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StageAdapter mAdapter;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User user = (User) getIntent().getSerializableExtra("User");
        Toast.makeText(this, "Bienvenido: " + user.getName() + " " + user.getLastName(), Toast.LENGTH_LONG).show();

        prepareStageData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getBaseContext(), ChatbotActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fabIncident = findViewById(R.id.fabincidendent);
        fabIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getBaseContext(), IncidentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareStageData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, String.format(UrlConnection.URL_CONECCTION, "authentication/stages"),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String rpt = response.toString();
                    if (response.length() > 0) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                        gson = gsonBuilder.create();
                        //User u = gson.fromJson(rpt, User.class);
                        StageResult stageResponse = gson.fromJson(rpt, StageResult.class);
                        if (stageResponse.getTypeResult() == 0) {
                            if (stageResponse.getListResult().size() > 0) {
                                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                                mAdapter = new StageAdapter(stageResponse.getListResult());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(HomeActivity.this, "No hay etapas para mostrar", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(HomeActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
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
                Toast.makeText(HomeActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
            }
        });
        AppController.getInstance(HomeActivity.this).addToRequestQueue(jsonObjectRequest);
    }
}
