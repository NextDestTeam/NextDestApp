package com.nextdest.nextdest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nextdest.database.DB;
import com.nextdest.database.models.Person;
import com.nextdest.service.PersonService;

public class Register extends AppCompatActivity {
     EditText email;
     EditText username;
     EditText pass;
     EditText repass;
     Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB db =new DB(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        username=findViewById(R.id.userName);
        pass=findViewById(R.id.pass);
        repass=findViewById(R.id.repass);
        reg=findViewById(R.id.btn_register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password=pass.getText().toString();
                String repassword=repass.getText().toString();
                String UserName=username.getText().toString();
                String Email=email.getText().toString();

              if(password.equals(repassword))  {

                  Person person = new Person();
                  person.setPersonTypeId(1);
                  person.setEmail(Email);
                  PersonService personService = new PersonService(getApplicationContext());
                  long personId = personService.save(person);

                 long id= db.addNew_LOGIN(UserName,password,Email,(int)personId);

                 Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_LONG).show();

                  }
                  else{
                      Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_LONG).show();

                  }

            }
        });
    }
}
