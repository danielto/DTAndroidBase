//package cz.tokar.android.app.myarchitecture1.repo
//
//import android.arch.lifecycle.LiveData
//import cz.tokar.android.app.myarchitecture1.helper.AppExecutors
//import cz.tokar.android.app.myarchitecture1.network.ApiServices
//import cz.tokar.android.app.myarchitecture1.network.model.base.Resource
//import javax.inject.Inject
//
//class PostRepository @Inject constructor(
//  private val apiService: ApiServices,
//  private val feedDao: FeedDao,
//  private val appExecutors: AppExecutors = AppExecutors()
//) {
//
//  fun getFeeds(): LiveData<Resource<List<Feed>?>> = object : NetworkBoundResource<List<Feed>, List<Feed>>(appExecutors) {
//    override fun saveCallResult(item: List<Feed>) {
//      feedDao.insertAll(item)
//    }
//
//    override fun shouldFetch(data: List<Feed>?): Boolean = data == null || data.isEmpty()
//
//    override fun loadFromDb(): LiveData<List<Feed>> = feedDao.getFeeds()
//
//    override fun createCall(): LiveData<ApiResponse<List<Feed>>> = apiService.getPosts()
//  }.asLiveData()
//}