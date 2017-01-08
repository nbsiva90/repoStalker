package com.bypassmobile.octo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bypassmobile.octo.adapter.RecyclerMembersAdapter;
import com.bypassmobile.octo.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerMembers;
    private LinearLayoutManager mLayoutManagerMembers;
    private RecyclerView.Adapter mAdapter;

    private void initUIReferences(){
        mRecyclerMembers = (RecyclerView) findViewById(R.id.recyclerView_members);
        mLayoutManagerMembers = new LinearLayoutManager(this);
        mRecyclerMembers.setLayoutManager(mLayoutManagerMembers);


    }

    private void init(){


        getEndpoint().getOrganizationMember("bypasslane", new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                mAdapter = new RecyclerMembersAdapter(getApplicationContext(), users);
                mRecyclerMembers.setAdapter(mAdapter);
            }

            @Override
            public void failure(RetrofitError error) {}
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIReferences();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //fetchOrganisationMembers();
    }

    private void fetchOrganisationMembers(){
        getEndpoint().getOrganizationMember("bypasslane", new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
