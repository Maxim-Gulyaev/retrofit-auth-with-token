package android.maxim.retrofitauthtoken.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface Api {

    @POST("auth/login")
    fun auth(@Body authData: AuthData): Single<User>

    @GET("auth/products")
    fun getProducts(@Header("Authorization") token: String ): Products

}