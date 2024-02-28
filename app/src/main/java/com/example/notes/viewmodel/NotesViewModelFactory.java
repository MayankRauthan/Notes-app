package com.example.notes.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NotesViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mUserId;

    public NotesViewModelFactory(Application application, String userId) {
        mApplication = application;
        mUserId = userId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NotesViewModel(mApplication, mUserId);
    }
}
