package android.maxim.retrofitauthtoken.di;

import android.maxim.retrofitauthtoken.model.Api;
import android.maxim.retrofitauthtoken.model.User;
import androidx.lifecycle.MutableLiveData;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    @TokenQualifier
    MutableLiveData<String> provideMutableLiveDataToken() {
        return new MutableLiveData<>();
    }

    @Provides
    @Singleton
    @ErrorQualifier
    MutableLiveData<String> provideMutableLiveDataError() {
        return new MutableLiveData<>();
    }

    @Provides
    @Singleton
    MutableLiveData<User> provideMutableLiveDataUser() {
        return new MutableLiveData<>();
    }

    @Provides
    @Singleton
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    Api provideApi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(Api.class);
    }

}
