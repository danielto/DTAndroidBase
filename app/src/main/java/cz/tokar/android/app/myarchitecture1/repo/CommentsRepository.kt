package cz.tokar.android.app.myarchitecture1.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import cz.tokar.android.app.myarchitecture1.App
import cz.tokar.android.app.myarchitecture1.database.dao.CommentDao
import cz.tokar.android.app.myarchitecture1.database.entity.Comment
import cz.tokar.android.app.myarchitecture1.helper.AppExecutors
import cz.tokar.android.app.myarchitecture1.network.ApiServices
import cz.tokar.android.app.myarchitecture1.network.model.base.Resource
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class CommentsRepository @Inject constructor(
  private val commentDao: CommentDao,
  private val retrofit: Retrofit,
  private val appExecutors: AppExecutors = AppExecutors()
) {

  val TAG = CommentsRepository::class.java.simpleName

  /**
   * Fetch the news articles from database if exist else fetch from web
   * and persist them in the database
   */

  fun getComments(): LiveData<Resource<List<Comment>?>> {
    Timber.v("[%s]::[getComments]", TAG)

    return object : NetworkBoundResource<List<Comment>, List<Comment>>(appExecutors) {
      override fun saveCallResult(users: List<Comment>) {
        commentDao.saveAllComments(users)
      }

      override fun shouldFetch(data: List<Comment>?) = true

      override fun loadFromDb() = commentDao.getAllComments()

      override fun createCall() = retrofit.create(ApiServices::class.java).getComments()
    }.asLiveData()
  }

  fun refreshCommets(): LiveData<Resource<List<Comment>?>> {
    Timber.v("[%s]::[refreshCommets]", TAG)

    return object : NetworkBoundResource<List<Comment>, List<Comment>>(appExecutors) {
      override fun saveCallResult(users: List<Comment>) {
//        commentDao.deleteAll()
        commentDao.saveAllComments(users)
      }

      override fun shouldFetch(data: List<Comment>?): Boolean {
        return data == null || data.isEmpty() || !isUpToDate()
      }

      override fun loadFromDb() = commentDao.getAllComments()

      override fun createCall() = retrofit.create(ApiServices::class.java).getComments()
    }.asLiveData()
  }

  fun getCommentById(id: Long): LiveData<Comment?> {
    return commentDao.getCommentById(id)
  }

  private fun isUpToDate() = false

}