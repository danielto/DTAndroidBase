package cz.tokar.android.app.myarchitecture1.ui.presenter.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cz.tokar.android.app.myarchitecture1.database.entity.User
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.network.model.base.Resource
import cz.tokar.android.app.myarchitecture1.repo.UserRepository
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
  mPreferenceHelper: PreferenceHelper,
  mUserRepository: UserRepository

): ViewModel() {

  private val TAG = MainViewModel::class.java.simpleName

  private var mUsers: MutableLiveData<Resource<List<User>?>> = MutableLiveData()

  init {
//    users.postValue(mUserRepository.getUsers())
    Timber.d("[%s]::[init]::[firstRun %b]", TAG, mPreferenceHelper.getFirstRun())
    mPreferenceHelper.setUserPhoneNumber("+420 777 123 654")
  }
}
