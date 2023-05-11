package android.maxim.retrofitauthtoken.ui.authscreen;

import android.maxim.retrofitauthtoken.model.Api;
import android.maxim.retrofitauthtoken.model.AuthData;
import android.maxim.retrofitauthtoken.model.User;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    @Inject
    AuthViewModel() {}

    @Inject
    public MutableLiveData<String> token;
    MutableLiveData<User> mutableLiveDataUser = new MutableLiveData<>();
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    Api api;

    //TODO Handle the error in auth
    void auth(String username, String password) {

        AuthData authData = new AuthData(username, password);

        compositeDisposable.add(
                api.auth(authData)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                user -> {
                                    mutableLiveDataUser.setValue(user);
                                    token.setValue(user.token);
                                },
                                user -> {}
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
