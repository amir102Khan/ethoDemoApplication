package com.amir.ethodemoapplication.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.interfaces.Constants;
import com.amir.ethodemoapplication.model.UserModel;
import com.amir.ethodemoapplication.util.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileViewModel extends ViewModel implements Constants {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<String> bike = new MutableLiveData<>();

    private FirebaseUser firebaseUser = FirebaseAuth.
            getInstance().getCurrentUser();

    private DatabaseReference databaseReference = FirebaseDatabase
            .getInstance().getReference(USER).child(firebaseUser.getUid());

    private Context context;
    private AuthListener dataListener;

    public void setDataListener(AuthListener dataListener) {
        this.dataListener = dataListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private MutableLiveData<UserModel> userMutableLiveData;

    public LiveData<UserModel> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
            loadData();
        }

        return userMutableLiveData;
    }

    /**
     * method to load data from firebase
     */

    private void loadData() {

        if (!Common.checkInternetConnection(context)) {
            dataListener.onError("check your internet connectivity");
        } else {
            dataListener.onStarting();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        UserModel userModel = dataSnapshot.getValue(UserModel.class);
                        userMutableLiveData.setValue(userModel);
                    }
                    dataListener.onSuccess();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    dataListener.onError(databaseError.getMessage());
                }
            });
        }
    }

    /**
     * method called when update profile button is clicked
     */
    public void onUpdateProfile() {
        final UserModel userModel = new UserModel(name.getValue(),
                email.getValue(),
                firebaseUser.getUid(),
                phone.getValue(),
                bike.getValue());

        if (!Common.validateEditText(userModel.getName())) {
            dataListener.onValidationError("Name is empty");
        } else if (!Common.validateEditText(userModel.getPhone())) {
            dataListener.onValidationError("Mobile number is empty");
        } else if (userModel.getPhone().length() < 10) {
            dataListener.onValidationError("Enter a valid mobile number");
        } else if (!Common.validateEditText(userModel.getBike())) {
            dataListener.onValidationError("Bike name is empty");
        } else {
            if (!Common.checkInternetConnection(context)) {
                dataListener.onValidationError("Bike name is empty");
            } else {
                dataListener.onStarting();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        databaseReference.setValue(userModel);

                        dataListener.onSuccess();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        dataListener.onError(databaseError.getMessage());
                    }
                });
            }
        }

    }
}
