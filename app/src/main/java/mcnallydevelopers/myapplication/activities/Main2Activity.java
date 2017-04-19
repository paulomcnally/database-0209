package mcnallydevelopers.myapplication.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import mcnallydevelopers.myapplication.R;
import mcnallydevelopers.myapplication.models.User;

public class Main2Activity extends AppCompatActivity {

    private EditText name;
    private EditText phoneNumber;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        name = (EditText) findViewById(R.id.name);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                finish();
            }
        });
    }

    private boolean validate() {
        boolean success = false;
        if(name.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar un nombre", Toast.LENGTH_LONG).show();
        } else if(phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar un numero celular", Toast.LENGTH_LONG).show();
        } else {
            success = true;
        }

        return success;
    }

    private void save() {
        if (validate()) {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            User user = realm.createObject(User.class);
            user.setName(name.getText().toString());
            user.setPhoneNumber(phoneNumber.getText().toString());
            realm.commitTransaction();
        }
    }

}
