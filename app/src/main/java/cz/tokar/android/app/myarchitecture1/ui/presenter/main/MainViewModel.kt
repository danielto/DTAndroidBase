package cz.tokar.android.app.myarchitecture1.ui.presenter.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cz.tokar.android.app.myarchitecture1.core.AppInjector.init
import cz.tokar.android.app.myarchitecture1.database.entity.Comment
import cz.tokar.android.app.myarchitecture1.database.entity.User
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.network.model.base.Resource
import cz.tokar.android.app.myarchitecture1.repo.CommentsRepository
import cz.tokar.android.app.myarchitecture1.repo.UserRepository
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
  val mPreferenceHelper: PreferenceHelper,
  val mCommentsRepository: CommentsRepository
): ViewModel() {

  private val TAG = MainViewModel::class.java.simpleName

  private var comments: LiveData<Resource<List<Comment>?>>
  private var mediatorLiveData = MediatorLiveData<Resource<List<Comment>?>>()

  init {
    Timber.d("[%s]::[init]::[firstRun %b]", TAG, mPreferenceHelper.getFirstRun())
    mPreferenceHelper.setUserPhoneNumber("+420 777 123 654")

    comments = mCommentsRepository.getComments()
    mediatorLiveData.addSource(comments, {mediatorLiveData.value = it})
  }

  fun getComments() = mediatorLiveData

  fun refreshComments() {
    Timber.v("[%s]::[refresh comments]", TAG)
    mediatorLiveData.removeSource(comments)
    comments = mCommentsRepository.refreshCommets()
    mediatorLiveData.addSource(comments, {mediatorLiveData.value = it})
  }
}
