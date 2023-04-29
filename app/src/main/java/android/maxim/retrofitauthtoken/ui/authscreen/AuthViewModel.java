package android.maxim.retrofitauthtoken.ui.authscreen;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    @Inject
    AuthViewModel() {}

    @Inject
    MutableLiveData<String> token;
}
