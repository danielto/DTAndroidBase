package cz.tokar.android.app.myarchitecture1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import cz.tokar.android.app.myarchitecture1.core.AppInjector
import cz.tokar.android.app.myarchitecture1.core.component.AppComponent
import cz.tokar.android.app.myarchitecture1.core.component.DaggerAppComponent
import cz.tokar.android.app.myarchitecture1.core.module.AppModule
import cz.tokar.android.app.myarchitecture1.core.module.NetworkModule
import cz.tokar.android.app.myarchitecture1.utils.DebugTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

  val TAG = App::class.java.simpleName

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    sContext = this
    sResources = resources
    AppInjector.init(this)
    initTimber()
  }

  override fun activityInjector() = dispatchingAndroidInjector


  private fun initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      Timber.plant(DebugTree())
    }
  }

  companion object {

    /**
     * Application context.
     */
    @SuppressLint("StaticFieldLeak")
    private lateinit var sContext: Context

    @JvmStatic
    private lateinit var sAppComponent: AppComponent

    /**
     * Application resources.
     */
    @JvmStatic
    private var sResources: Resources? = null

    /**
     * @see Resources.getString
     */
    fun getResString(id: Int): String {
      return sResources!!.getString(id)
    }

    /**
     * @see Resources.getQuantityString
     */
    fun getResPlural(id: Int, count: Int, vararg formatArgs: Any): String {
      return sResources!!.getQuantityString(id, count, *formatArgs)
    }

  }
}