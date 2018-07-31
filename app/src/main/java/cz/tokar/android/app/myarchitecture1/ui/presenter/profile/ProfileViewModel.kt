package cz.tokar.android.app.myarchitecture1.ui.presenter.profile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.repo.UserRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
  mPreferenceHelper: PreferenceHelper,
  mUserRepository: UserRepository

): ViewModel() {

  private var userPhoneNumber: MutableLiveData<String> = MutableLiveData()
  private var mUserEmail: MutableLiveData<String> = MutableLiveData()
  private val mUserId = mPreferenceHelper.getUserId()

  init {
    userPhoneNumber.postValue(mPreferenceHelper.getUserPhoneNumber())
    mUserEmail.postValue("text@sads.sk")
  }


  fun getUserPhoneNumber() = userPhoneNumber

  fun getUserEmail() = mUserEmail
}
