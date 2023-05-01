package android.maxim.retrofitauthtoken.model;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    @POST("auth/login")
    Single<User> auth(@Body AuthData authData);

    @GET("auth/products")
    Products getProducts(@Header("Authorization") String token);

}
