package cz.tokar.android.app.myarchitecture1.core.module

import cz.tokar.android.app.myarchitecture1.ui.presenter.commentdetail.CommentDetailFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.favorite.FavoriteFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.history.HistoryFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// TODO add new fragments here

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeMainFragment(): MainFragment

  @ContributesAndroidInjector
  abstract fun contributeFavoriteFragment(): FavoriteFragment

  @ContributesAndroidInjector
  abstract fun contributeProfileFragment(): ProfileFragment

  @ContributesAndroidInjector
  abstract fun contributeHistoryFragment(): HistoryFragment

  @ContributesAndroidInjector
  abstract fun contributeCommentDetailFragment(): CommentDetailFragment

//  @ContributesAndroidInjector
//  abstract fun contributeBaseFragment(): BaseFragment

}