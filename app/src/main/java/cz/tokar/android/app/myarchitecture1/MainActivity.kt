package cz.tokar.android.app.myarchitecture1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import cz.tokar.android.app.myarchitecture1.helper.NavigationHelper
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment.newInstance())
        .commitNow()
    }

    navigation.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.action_home -> NavigationHelper.showHomeFragment(this@MainActivity, R.id.container, false)
        R.id.action_favorite -> NavigationHelper.showFavoriteFragment(this@MainActivity, R.id.container, false)
        R.id.action_profile -> NavigationHelper.showProfileFragment(this@MainActivity, R.id.container, false)
      }
      return@setOnNavigationItemSelectedListener true
    }

  }

  override fun supportFragmentInjector() = dispatchingAndroidInjector
}
