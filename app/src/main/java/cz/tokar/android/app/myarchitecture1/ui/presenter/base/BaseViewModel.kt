//package cz.tokar.android.app.myarchitecture1.ui.presenter.base
//
//import android.arch.lifecycle.ViewModel
//import cz.tokar.android.app.myarchitecture1.core.component.DaggerViewModelComponent
//import cz.tokar.android.app.myarchitecture1.core.module.AppModule
//import cz.tokar.android.app.myarchitecture1.core.module.NetworkModule
//import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
//import cz.tokar.android.app.myarchitecture1.repo.UserRepository
//import javax.inject.Inject
//
//// tutorial to inject dependecy with DAGGER 2 to ViewModel
//// https://proandroiddev.com/mvvm-with-kotlin-android-architecture-components-dagger-2-retrofit-and-rxandroid-1a4ebb38c699
//
//open class BaseViewModel : ViewModel(){
//
//  private val injector = DaggerViewModelComponent
//    .builder()
//    .networkModule(NetworkModule())
//    .appModule(AppModule())
//    .build()
//
//  init {
//    inject()
//  }
//
//  /**
//   * Injects the required dependencies
//   */
//  private fun inject() {
//    when (this) {
//      is BaseViewModel -> injector.inject(this)
//    }
//  }
//
//}