package com.nextdest.nextdest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;


import com.google.android.gms.common.util.IOUtils;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class EditUserProfile extends Fragment {

    public static final int RESULT_LOAD_IMAGE = 1;
    public View view;
    DB db;
    Context context;
    int id;
    String name;
    String email;
    String pass;
    String confpass;
    String userName="";
    String emailPattern;

    public static EditUserProfile newInstance(String userName){
        EditUserProfile editUserProfile = new EditUserProfile();
        editUserProfile.userName = userName;
        return editUserProfile;
    }

    private Button btnSave;
    public ImageView imgProfilePic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = new DB(getContext());


        view = inflater.inflate(R.layout.fragment_edit_user_profile, container, false);

        TextView tvUsername = view.findViewById(R.id.edUserName);
        TextView tvEmail = view.findViewById(R.id.edEmail);

        TextView tvOldPassword = view.findViewById(R.id.oldPass);
        TextView tvNewPassword = view.findViewById(R.id.newPass);
        imgProfilePic = view.findViewById(R.id.edPropic);

        btnSave = view.findViewById(R.id.btnSave);
      //  TextView tvProfName = view.findViewById(R.id.edProfName);
        Cursor cursor = db.get_LOGIN_Data(userName);
        while(cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(MySQLiteDatabase.KEY_ID));
            name = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.LOGIN_NAME));
            email = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.EMAIL));
            pass = cursor.getString(cursor.getColumnIndex(MySQLiteDatabase.PASSWORD));
            tvEmail.setText(email);
            tvUsername.setText(userName);
            tvOldPassword.setText(pass);
        }
        cursor.close();
        cursor= db.get_ProfileImage_Data(id);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(MySQLiteDatabase.PIMAGE);
            byte[] photoByte = cursor.getBlob(index);
            Bitmap bitmap = BitmapFactory.decodeByteArray(photoByte,0,photoByte.length);
            imgProfilePic.setImageBitmap(bitmap);
        }
        cursor.close();

        Button btnPropic = (Button) view.findViewById(R.id.btnEdPropic);
        btnPropic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        tvUsername.setText(name);
        tvEmail.setText(email);

       //validate entered email
        email = tvEmail.getText().toString().trim();

        emailPattern = "([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

        tvEmail .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (email.matches(emailPattern))
                {
                    Toast.makeText(getActivity().getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    // or
                    //textView.setText("valid email");
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    //or
                   // textView.setText("invalid email");
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

       // tvProfName.setText("Leo Messi");
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

            try {
                InputStream is = this.getContext().getContentResolver().openInputStream(selectedImage);

                byte[] photo = IOUtils.toByteArray(is);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Bitmap bmp = BitmapFactory.decodeByteArray(photo,0,photo.length);

                bmp.compress(Bitmap.CompressFormat.JPEG,70,bos);
                db.addNew_PROFILE_IMAGE(id,bos.toByteArray());
                //imgProfilePic= view.findViewById(R.id.edPropic);
                imgProfilePic.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}