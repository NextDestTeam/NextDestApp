package com.nextdest.nextdest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;


import org.w3c.dom.Text;

public class EditUserProfile extends Fragment {

    public static final int RESULT_LOAD_IMAGE = 1;
    public View view;

    Context context;

    private Button btnSave;
    public ImageView imgProfilePic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_user_profile, container, false);
        TextView tvUsername = view.findViewById(R.id.edUserName);
        TextView tvEmail = view.findViewById(R.id.edEmail);
        TextView tvProfName = view.findViewById(R.id.edProfName);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setEnabled(false);

        Button btnPropic = (Button) view.findViewById(R.id.btnEdPropic);
        btnPropic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        tvUsername.setText("leo");
        tvEmail.setText("leom@gmail.com");
        tvProfName.setText("Leo Messi");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = context.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imgProfilePic= view.findViewById(R.id.edPropic);
            imgProfilePic.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

}