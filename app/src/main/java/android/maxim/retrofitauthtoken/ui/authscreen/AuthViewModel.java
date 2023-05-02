package android.maxim.retrofitauthtoken.ui.authscreen;

import android.maxim.retrofitauthtoken.model.Api;
import android.maxim.retrofitauthtoken.model.AuthData;
import android.maxim.retrofitauthtoken.model.User;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    @Inject
    AuthViewModel() {}

    @Inject
    public MutableLiveData<String> token;
    MutableLiveData<User> mutableLiveDataUser = new MutableLiveData<>();
    @Inject
    Api api;

    //TODO Handle the error in auth
    void auth(String username, String password) {

        AuthData authData = new AuthData(username, password);

        api.auth(authData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            mutableLiveDataUser.setValue(user);
                            token.setValue(user.token);
                        },
                        user -> {}
                );
    }


}
