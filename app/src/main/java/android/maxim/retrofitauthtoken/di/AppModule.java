package android.maxim.retrofitauthtoken.di;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    MutableLiveData<String> provideMutableLiveData() {
        return new MutableLiveData<>();
    }
}
