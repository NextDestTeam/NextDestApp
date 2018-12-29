package com.nextdest.synchronization;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.nextdest.database.models.Activity;
import com.nextdest.database.models.ActivityType;
import com.nextdest.database.models.Image;
import com.nextdest.database.models.Login;
import com.nextdest.database.models.Person;
import com.nextdest.database.models.PersonActivityComment;
import com.nextdest.database.models.PersonPreference;
import com.nextdest.database.models.PersonType;
import com.nextdest.database.models.Reaction;

import org.json.JSONObject;

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    // Global variables
    JSONObject JsonObject;
    Activity activity;
    ActivityType activityType;
    Image image;
    Login login;
    Person person;
    PersonActivityComment personActivityComment;
    PersonPreference personPreference;
    PersonType personType;
    Reaction reaction;

    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;
    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        /*HttpGetRequest httpGetRequest = new HttpGetRequest();
        final String HTTP_METHOD = "GET";
        String url = httpGetRequest.getURL() + "loginName/" + etLogin.getText().toString();
        String result;
        JSONObject jsonObject;

        result = httpGetRequest.execute(url, HTTP_METHOD).get();

        jsonObject = new JSONObject(result);
        String userName = jsonObject.getString("login");
        String password = jsonObject.getString("password");

        HttpPostRequest httpPostRequestLogin = new HttpPostRequest();
        HttpPostRequest httpPostRequestPerson = new HttpPostRequest();
        String urlLogin = httpPostRequestLogin.getUrl() + "logins";
        String urlPerson = httpPostRequestPerson.getUrl() + "persons";
        String resultLogin;
        String resultPerson;
        JSONObject jsonObjectLogin = new JSONObject();
        JSONObject jsonObjectPerson = new JSONObject();

        // Create person
        jsonObjectPerson.put("age", "2018-12-08T17:39:10.103Z");
        jsonObjectPerson.put("email", etEmail.getText().toString());
        jsonObjectPerson.put("firstName", etUserName.getText().toString());
        jsonObjectPerson.put("lastName", etUserName.getText().toString());
        jsonObjectPerson.put("personTypeId", 1);

        httpPostRequestPerson.setPostData(jsonObjectPerson);

        httpPostRequestPerson.execute(urlPerson).get();
        resultPerson = httpPostRequestPerson.getResponse();
        JSONObject personResponse = new JSONObject(resultPerson);*/
    }
}
