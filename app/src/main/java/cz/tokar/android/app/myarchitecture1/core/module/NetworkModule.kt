package cz.tokar.android.app.myarchitecture1.core.module

import android.content.Context
import com.google.gson.GsonBuilder
import cz.tokar.android.app.myarchitecture1.BuildConfig
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.network.NetworkInterceptor
import cz.tokar.android.app.myarchitecture1.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(networkInterceptor: NetworkInterceptor , loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return createApiClient(networkInterceptor, loggingInterceptor)
  }

  /**
   * Provides the Retrofit object.
   * @return the Retrofit object
   */
  @Provides
  @Reusable
  fun provideRetrofitInterface(
    context: Context,
    client: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
  ): Retrofit {
    val url = if (BuildConfig.DEBUG) {
      context.resources.getString(R.string.base_url_debug)
    } else {
      context.resources.getString(R.string.base_url)
    }

    return Retrofit.Builder()
      .baseUrl(url)
      .client(client)
      .addConverterFactory(gsonConverterFactory)
      .addCallAdapterFactory(LiveDataCallAdapterFactory())
//      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
      .build()
  }

  @Provides
  @Singleton
  fun provideGson(): GsonConverterFactory {
    val gson = GsonBuilder()
      .create()
    return GsonConverterFactory.create(gson)
  }

  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
  }

  @Provides
  @Singleton
  internal fun provideNetworkInterceptor(preferenceHelper: PreferenceHelper): NetworkInterceptor {
    return NetworkInterceptor(preferenceHelper)
  }

  /**
   * Creates OkHttp client with 10MiB cache.

   * @return Instance of OkHttp client.
   */
  private fun createApiClient(networkInterceptor: NetworkInterceptor , loggingInterceptor: HttpLoggingInterceptor ): OkHttpClient {

    val builder = OkHttpClient.Builder()
    builder.addInterceptor(networkInterceptor)
    if (BuildConfig.DEBUG) {
      builder.addInterceptor(loggingInterceptor)
    }
    return builder.build()
  }
}