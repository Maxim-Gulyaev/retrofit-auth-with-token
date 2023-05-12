package android.maxim.retrofitauthtoken.ui.authscreen;

import android.maxim.retrofitauthtoken.model.Api;
import android.maxim.retrofitauthtoken.model.AuthData;
import android.maxim.retrofitauthtoken.model.User;
import android.util.Log;
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
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    Api api;
    MutableLiveData<User> mutableLiveDataUser = new MutableLiveData<>();
    MutableLiveData<String> mutableLiveDataError = new MutableLiveData<>();
    private static final String TAG = "ERROR";

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
                                error -> {
                                    Log.e(TAG, error.getMessage());
                                    mutableLiveDataError.setValue(error.getMessage());
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
