package cz.tokar.android.app.myarchitecture1.core.component

import cz.tokar.android.app.myarchitecture1.App
import cz.tokar.android.app.myarchitecture1.core.module.AppModule
import cz.tokar.android.app.myarchitecture1.core.module.MainActivityModule
import cz.tokar.android.app.myarchitecture1.core.module.NetworkModule
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Singleton
@Component(modules = [
  AndroidInjectionModule::class,
  AppModule::class,
  NetworkModule::class,
  MainActivityModule::class
]
)

interface AppComponent /*: AndroidInjector<App>*/ {

//  fun providePreferenceHelper(): PreferenceHelper
//
//  fun provideRetrofit(): Retrofit

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun appModule(appModule: AppModule): Builder

    @BindsInstance
    fun networkModule(netModule: NetworkModule): Builder

    @BindsInstance
    fun application(application: App): Builder

    fun build(): AppComponent


  }

  fun inject(app: App)
}