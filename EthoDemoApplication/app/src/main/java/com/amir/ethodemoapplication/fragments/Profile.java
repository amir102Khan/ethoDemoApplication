package com.amir.ethodemoapplication.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseFragment;
import com.amir.ethodemoapplication.databinding.FragmentProfileBinding;
import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.model.UserModel;
import com.amir.ethodemoapplication.viewmodel.ProfileViewModel;


public class Profile extends BaseFragment implements AuthListener {


    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = (ConstraintLayout) binding.loader.getRoot();

        setViewModel();
        getData();
    }

    /**
     * method to set the view model
     */
    private void setViewModel() {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        binding.setProfileViewModel(profileViewModel);
        binding.setLifecycleOwner(this);
        profileViewModel.setContext(getActivity());
        profileViewModel.setDataListener(this);
    }

    /**
     * method to get data from firebase
     */
    private void getData() {
        profileViewModel.getUser().observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                setData(userModel);
            }
        });
    }


    /**
     * method to set data fetched from firebase
     */
    private void setData(UserModel userModel) {
        if (userModel.getName() != null) {
            binding.edName.setText(userModel.getName());
        }

        if (userModel.getEmail() != null) {
            binding.edEmail.setText(userModel.getEmail());
        }

        if (userModel.getPhone() != null) {
            binding.edPhone.setText(userModel.getPhone());
        }

        if (userModel.getBike() != null) {
            binding.edBike.setText(userModel.getBike());
        }
    }


    @Override
    public void onStarting() {
        showLoader();
        hideKeyboard();
    }

    @Override
    public void onSuccess() {
        hideLoader();
    }

    @Override
    public void onError(String message) {
        hideLoader();
        showToast(message);
    }

    @Override
    public void onValidationError(String message) {
        showToast(message);
    }
}
