package cz.tokar.android.app.myarchitecture1.ui.presenter.favorite

import android.arch.lifecycle.ViewModel
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.repo.UserRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
  mPreferenceHelper: PreferenceHelper,
  mUserRepository: UserRepository

): ViewModel() {

  init {
  }
}
