package cz.tokar.android.app.myarchitecture1.repo

import android.arch.lifecycle.LiveData
import cz.tokar.android.app.myarchitecture1.database.dao.UserDao
import cz.tokar.android.app.myarchitecture1.database.entity.User
import cz.tokar.android.app.myarchitecture1.helper.AppExecutors
import cz.tokar.android.app.myarchitecture1.network.ApiServices
import cz.tokar.android.app.myarchitecture1.network.model.base.Resource
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val userDao: UserDao,
  private val retrofit: Retrofit,
  private val appExecutors: AppExecutors = AppExecutors()
) {


  /**
   * Fetch the news articles from database if exist else fetch from web
   * and persist them in the database
   */
  fun getUsers(): LiveData<Resource<List<User>?>> {
    return object : NetworkBoundResource<List<User>, List<User>>(appExecutors) {
      override fun saveCallResult(users:  List<User>) {
        userDao.saveAll(users)
      }

      override fun shouldFetch(data: List<User>?) = true

      override fun loadFromDb() = userDao.getAllUsers()

      override fun createCall() = retrofit.create(ApiServices::class.java).getUsers()
    }.asLiveData()
  }

  fun getUser(id: Long): LiveData<Resource<User?>> {
    return object : NetworkBoundResource<User, User>(appExecutors) {
      override fun saveCallResult(user: User) {
        userDao.save(user)
      }

      override fun shouldFetch(data: User?) = true

      override fun loadFromDb() = userDao.getUserById(id)

      override fun createCall() = retrofit.create(ApiServices::class.java).getUserById(id)
    }.asLiveData()
  }

}