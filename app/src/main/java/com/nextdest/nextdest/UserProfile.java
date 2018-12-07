package com.nextdest.nextdest;

import android.content.Context;
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

public class UserProfile extends Fragment {
    Context context;
    MyAdapter myAdapter;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        button = view.findViewById(R.id.btnEdProfile);
        TextView tvUsername = view.findViewById(R.id.userName);
        TextView tvEmail = view.findViewById(R.id.email);
        TextView tvProfName = view.findViewById(R.id.profName);
        ImageView imgProfilePic = view.findViewById(R.id.imgProfPic);

        tvUsername.setText("leo");
        tvEmail.setText("leom@gmail.com");
        tvProfName.setText("Leo Messi");
        imgProfilePic.setImageResource(R.drawable.barca);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {FragmentTransaction fr = getFragmentManager().beginTransaction();
           fr.replace(R.id.content_frame, new EditUserProfile());
           fr.commit();
            }
        });
        return view;
    }


}
