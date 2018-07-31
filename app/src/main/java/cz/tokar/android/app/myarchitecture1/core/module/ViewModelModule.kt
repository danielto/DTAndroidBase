package cz.tokar.android.app.myarchitecture1.core.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import cz.tokar.android.app.myarchitecture1.ui.presenter.base.MyViewModelFactory
import cz.tokar.android.app.myarchitecture1.ui.presenter.favorite.FavoriteViewModel
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainViewModel
import cz.tokar.android.app.myarchitecture1.ui.presenter.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// TODO add view model here

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindMainViewModel(vm: MainViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(FavoriteViewModel::class)
  abstract fun bindFavoriteViewModel(vm: FavoriteViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(ProfileViewModel::class)
  abstract fun bindProfileViewModel(vm: ProfileViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}