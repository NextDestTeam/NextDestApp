package com.nextdest.nextdest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdest.database.DB;
import com.nextdest.database.MySQLiteDatabase;

public class UserProfile extends Fragment {
    Context context;
    MyAdapter myAdapter;
    private Button button;
    DB db;
    String name;
    String email;
    String userName;
    Intent i;
    public static UserProfile newInstance(String userName){
        UserProfile userProfile =new UserProfile();
        userProfile.userName = userName;
        return userProfile;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = new DB(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        button = view.findViewById(R.id.btnEdProfile);
        TextView tvUsername = view.findViewById(R.id.userName);
        TextView tvEmail = view.findViewById(R.id.email);
       // TextView tvProfName = view.findViewById(R.id.profName);
        ImageView imgProfilePic = view.findViewById(R.id.imgProfPic);
        Bundle bundle = getArguments();
        userName= bundle.getString("userName");
        //Toast.makeText(getActivity().getApplicationContext(), userName, Toast.LENGTH_LONG).show();
       /* Cursor cursor = db.get_LOGIN_Data(userName);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.KEY_ID));
           name = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LOGIN_NAME));
            email = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.EMAIL));
        } */
        Fragment fragment = null;
        tvUsername.setText(name);
        tvEmail.setText(email);
       // tvProfName.setText("Leo Messi");
        //imgProfilePic.setImageResource(R.drawable.barca);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditUserProfile editUserProfile = EditUserProfile.newInstance(userName);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
           fr.replace(R.id.content_frame, editUserProfile);
           fr.addToBackStack(null);
           fr.commit();
            }
        });
        return view;
    }


}
