package nfnlabs.mtx1.firebaseintegration.FirebaseDatabase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import nfnlabs.mtx1.firebaseintegration.FirebaseDatabase.json_model.UserInfo;
import nfnlabs.mtx1.firebaseintegration.R;

public class SaveInDB extends AppCompatActivity implements View.OnClickListener{

    EditText etName;
    EditText etAge;
    Button btAddToDB;

    Firebase firebase_db_ref;

    //Class Object
    UserInfo mUserInfo;
    Config config;

//    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_in_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        config=new Config();


        //Creating firebase object
        firebase_db_ref = new Firebase(config.FIREBASE_URL);

        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);

        btAddToDB = (Button) findViewById(R.id.bt_add);
        btAddToDB.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.bt_add:

                saveData();
                break;
        }
    }

    public void saveData(){

        Log.i("DB", "Clicked ADD");

//                //Creating firebase object
//                firebase_db_ref = new Firebase(Config.FIREBASE_URL);

        //Getting values to store
        String name = etName.getText().toString().trim();
        String age = etAge.getText().toString().trim();

        Log.i("DB","Name : "+name);
        Log.i("DB", "Age : " + age);

        //Creating Person object
                mUserInfo = new UserInfo();

                //Adding values
                mUserInfo.setName(name);
                mUserInfo.setAge(age);

                //Storing values to firebase
                firebase_db_ref.child("UserInfo").setValue(mUserInfo);

//        mUserInfo = new UserInfo(name, age);
//        firebase_db_ref.push().setValue(mUserInfo);


        Log.i("DB", "DBName : " + mUserInfo.getName());
        Log.i("DB","DBAge : "+mUserInfo.getAge());

                //Retrieve the Data
                //Value event listener for realtime data update
                //ValueEventListener : Reads the entire value from the database

                firebase_db_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                            //Getting the data from snapshot
                            mUserInfo = postSnapshot.getValue(UserInfo.class);

                            //Adding it to a string
                            String str_userInfo = "Name: " + mUserInfo.getName() + "\nAddress: " + mUserInfo.getAge() + "\n\n";
                            Log.i("DBInfo","UserInfo : "+mUserInfo.getName()+" "+mUserInfo.getAge());
                            Log.i("DBInfo","Size : "+str_userInfo.length());
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.i("FirebaseDB Exception","The read failed:" + firebaseError.getMessage());
                    }
                });

//        }
//    });

    }

}
