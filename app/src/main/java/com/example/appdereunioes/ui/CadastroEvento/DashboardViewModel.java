package com.example.appdereunioes.ui.CadastroEvento;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel(MutableLiveData<String> mText) {

        this.mText = mText;
    }

    public LiveData<String> getText() {
        return mText;
    }
}