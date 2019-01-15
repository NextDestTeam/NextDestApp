package com.nextdest.synchronization;

import android.content.ContentResolver;
import android.content.Context;

import com.nextdest.models.Login;
import com.nextdest.nextdest.DB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Sync {
    private Context context;
    // Global variables
    JSONObject JsonObject;

    public Sync(Context context){
        this.context = context;
    }

    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;
    /**
     * Set up the sync adapter
     */

    public void sync() {
        HttpGetRequest httpGetRequest = new HttpGetRequest();
        final String HTTP_METHOD = "GET";
        String loginUrl = httpGetRequest.getURL() + "logins";
        String activityUrl = httpGetRequest.getURL() + "activities";
        String activityTypeUrl = httpGetRequest.getURL() + "activityTypes";
        String personTypeUrl = httpGetRequest.getURL() + "personTypes";
        String personUrl = httpGetRequest.getURL() + "persons";
        String commentUrl = httpGetRequest.getURL() + "personActivityComments";
        String reactionUrl = httpGetRequest.getURL() + "reactions";
        String preferenceUrl = httpGetRequest.getURL() + "preferences";
        String imageUrl = HttpGetRequest.getURL() + "images";
        String result;
        List<Login> logins = new ArrayList<>();
        JSONObject jsonObject;
        DB db = new DB(context);


        try {
            result = httpGetRequest.execute(activityTypeUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray activityTyepArray = new JSONArray(result);
                for (int i = 0; i < activityTyepArray.length(); i++) {
                    jsonObject = activityTyepArray.getJSONObject(i);
                    Integer activityTypeId = (Integer) jsonObject.get("id");
                    String activityTypeName = jsonObject.getString("name");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_ACTIVITY_TYPE(activityTypeName);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(personTypeUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray personTyepArray = new JSONArray(result);
                for (int i = 0; i < personTyepArray.length(); i++) {
                    jsonObject = personTyepArray.getJSONObject(i);
                    Integer activityTypeId = (Integer) jsonObject.get("id");
                    String activityTypeName = jsonObject.getString("name");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_PERSON_Type(activityTypeName);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(personUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray personArray = new JSONArray(result);
                for (int i = 0; i < personArray.length(); i++) {
                    jsonObject = personArray.getJSONObject(i);
                    Integer personId = (Integer) jsonObject.get("id");
                    String firstName = jsonObject.getString("firstName");
                    String lastName = jsonObject.getString("lastName");
                    String email = jsonObject.getString("email");
                    String age = (String) jsonObject.get("age");
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
                    Date birth = dt.parse(age);
                    String stringPersonTye = jsonObject.getString("personTypeId");
                    JSONObject jsonPersonType = new JSONObject(stringPersonTye);
                    Integer personType = (Integer) jsonPersonType.get("id");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_PERSON(firstName, lastName, email, birth, personType);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(loginUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray loginArray = new JSONArray(result);
                for (int i = 0; i < loginArray.length(); i++) {
                    jsonObject = loginArray.getJSONObject(i);
                    Integer id = (Integer) jsonObject.get("id");
                    String userName = jsonObject.getString("login");
                    String password = jsonObject.getString("password");
                    String personId = jsonObject.getString("personId");
                    JSONObject person = new JSONObject(personId);
                    String email = person.getString("email");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_LOGIN(email, userName, password);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(activityUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray activityArray = new JSONArray(result);
                for (int i = 0; i < activityArray.length(); i++) {
                    jsonObject = activityArray.getJSONObject(i);
                    String activityName = jsonObject.getString("name");
                    String shortDescription = jsonObject.getString("shortDescription");
                    String description = jsonObject.getString("description");
                    String location = jsonObject.getString("location");
                    Integer price = (Integer) jsonObject.get("price");
                    String activityPerson = jsonObject.getString("activityPerson");
                    JSONObject jsonPerson = new JSONObject(activityPerson);
                    Integer personId = (Integer) jsonPerson.get("id");
                    String date = jsonObject.getString("date");
                    Integer activityId = (Integer) jsonObject.get("id");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_ACTIVITY(activityName, shortDescription, description, location, price, personId, date, activityId);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(commentUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray commentArray = new JSONArray(result);
                for (int i = 0; i < commentArray.length(); i++) {
                    jsonObject = commentArray.getJSONObject(i);
                    Integer commentId = (Integer) jsonObject.get("id");
                    String comment = jsonObject.getString("comment");
                    Integer personId = (Integer) jsonObject.get("personId");
                    Integer activityId = (Integer) jsonObject.get("activityId");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_PERSON_ACTIVITY_COMMENT(comment, personId, activityId);
                }
            }


            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(reactionUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray reactionArray = new JSONArray(result);
                for (int i = 0; i < reactionArray.length(); i++) {
                    jsonObject = reactionArray.getJSONObject(i);
                    String reactionId = (String) jsonObject.get("id");
                    String reaction = jsonObject.getString("reaction");
                    Integer personId = (Integer) jsonObject.get("personId");
                    Integer activityId = (Integer) jsonObject.get("activityId");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_REACTION(reaction, personId, activityId);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(preferenceUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray preferenceArray = new JSONArray(result);
                for (int i = 0; i < preferenceArray.length(); i++) {
                    jsonObject = preferenceArray.getJSONObject(i);
                    Integer preferenceId = (Integer) jsonObject.get("id");
                    Integer personId = (Integer) jsonObject.get("personId");
                    Integer activityId = (Integer) jsonObject.get("activityId");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_PERSON_PREFERENCE(personId, activityId);
                }
            }

            httpGetRequest = new HttpGetRequest();
            result = httpGetRequest.execute(imageUrl, HTTP_METHOD).get();
            if(result != null){
                JSONArray imageArray = new JSONArray(result);
                for (int i = 0; i < imageArray.length(); i++) {
                    jsonObject = imageArray.getJSONObject(i);
                    Integer imageId = (Integer) jsonObject.get("id");
                    Integer activityId = (Integer) jsonObject.get("activityId");
                    byte[] image = (byte[]) jsonObject.get("image");
                    // Look if there is a row in the database that matches with the server, if it does update the row, else insert
                    db.addNew_ACTIVITY_IMAGE(activityId, image);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void postComment(Integer activityId, String comment, Integer personId){
        Map<String, String>  postData = new HashMap<>();
        postData.put("activityId", activityId.toString());
        postData.put("comment", comment);
        postData.put("personId", personId.toString());
        HttpPostRequest httpPostRequest = new HttpPostRequest(postData);
        String url = httpPostRequest.getUrl() + "comments";
        httpPostRequest.execute(url);
    }

    public void postReaction(Integer personId, Integer activityId, String comment){
        Map<String, String>  postData = new HashMap<>();
        postData.put("activityId", activityId.toString());
        postData.put("comment", comment);
        postData.put("personId", personId.toString());
        HttpPostRequest httpPostRequest = new HttpPostRequest(postData);
        String url = httpPostRequest.getUrl() + "reactions";
        httpPostRequest.execute(url);
    }
}
