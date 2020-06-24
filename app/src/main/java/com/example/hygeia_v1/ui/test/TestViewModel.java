package com.example.hygeia_v1.ui.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public TestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is TEXT fragment!!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}