package com.amir.ethodemoapplication.interfaces;

public interface AuthListener {
    void onStarting();

    void onSuccess();

    void onError(String message);

    void onValidationError(String message);
}
