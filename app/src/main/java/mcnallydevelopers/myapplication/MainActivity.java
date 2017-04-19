package mcnallydevelopers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.Util;
import mcnallydevelopers.myapplication.activities.Main2Activity;
import mcnallydevelopers.myapplication.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        getData();
    }

    private void getData(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<User> userRealmResults = realm.where(User.class).findAll();

        for(User user : userRealmResults) {
            Log.i("MainActivity", user.getName());
            Log.i("MainActivity", user.getPhoneNumber());

            updateItem(user);

            //deleteItem(user);
        }

    }

    private void updateItem(User user) {
        Realm realm = Realm.getDefaultInstance();

        String lol = String.format(Locale.US, getString(R.string.concat_lol_xd_fredy), user.getName(), "LOL");

        realm.beginTransaction();
        user.setName(lol);
        realm.commitTransaction();
    }

    private void deleteItem(User user) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        user.deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
