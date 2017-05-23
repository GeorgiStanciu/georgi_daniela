package com.example.georgi.shop.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.georgi.shop.Activities.LoginActivity;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnUserLogin;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Georgi on 02-May-17.
 */

public class LoginFirebase {

    private static FirebaseAuth firebaseAuth;
    private Activity activity;

    public LoginFirebase(Activity activity){

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.activity = activity;
    }

    public void login(String userName, String password){
        firebaseAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            addDatabase();
                        }
                        else{
                            Toast.makeText(activity, R.string.incorrect_login, Toast.LENGTH_SHORT).show();
                            GlobalBus.getBus().post(new OnUserLogin(false));

                        }
                    }
                });
    }

    public void register(final String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            addDatabase();
                        }
                        else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(activity, R.string.email_exists, Toast.LENGTH_SHORT).show();
                                GlobalBus.getBus().post(new OnUserLogin(false));

                            }
                        }
                    }
                });

    }


    public void forgot(String email){
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(activity, "succeed", Toast.LENGTH_SHORT).show();
                        }
                        GlobalBus.getBus().post(new OnUserLogin(false));
                    }
                });
    }

    public void addDatabase(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            String email = user.getEmail();
            String name = user.getDisplayName();
            /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
            UserModel newUser = new UserModel(userId, email, name, "", "");
            databaseReference.setValue(newUser);*/
            //setUserPreference(userId);
            UserModel userModel = new UserModel(email, userId, name);
            new SetUser(userModel).execute();
        }
        GlobalBus.getBus().post(new OnUserLogin(true));
    }

    public void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity, "Facebook login success", Toast.LENGTH_SHORT).show();
                            addDatabase();
                        }
                        else {
                            Toast.makeText(activity, "Facebook login failed", Toast.LENGTH_SHORT).show();
                            GlobalBus.getBus().post(new OnUserLogin(false));
                        }

                    }
                });
    }



    private void setUserPreference(int userId){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.preference), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(activity.getString(R.string.user_id_preference), userId);
        editor.commit();
    }

    public void logout(){

        firebaseAuth.signOut();
        setUserPreference(0);
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }


    class SetUser extends AsyncTask{

        UserModel user;
        int id;
        SetUser(UserModel user){
            this.user = user;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client =  new Client();
            client.connectToServer();
            CommandResponse rs = client.receiveDataFromServer(new Command(CommandEnum.AddUserCommand, user));
            id = (int) rs.getResponse();


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            setUserPreference(id);
        }
    }
}
