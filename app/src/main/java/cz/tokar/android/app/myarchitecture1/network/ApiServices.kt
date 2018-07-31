package cz.tokar.android.app.myarchitecture1.network

import android.arch.lifecycle.LiveData
import cz.tokar.android.app.myarchitecture1.database.entity.User
import cz.tokar.android.app.myarchitecture1.network.model.base.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
  @GET("users/{id}")
  fun getUserById(@Path("id") id: Long): LiveData<Resource<User>>

  @GET("users")
  fun getUsers(): LiveData<Resource<List<User>>>

}
