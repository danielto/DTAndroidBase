package cz.tokar.android.app.myarchitecture1.network

import cz.tokar.android.app.myarchitecture1.App
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(val mPreferenceHelper: PreferenceHelper) : Interceptor {

  companion object {
    private val HEADER_CONTENT_TYPE_KEY = "Content-Type"
    private val HEADER_CONTENT_TYPE_VALUE = "application/json; charset=utf-8"
  }

  /* Public Methods *******************************************************************************/

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain
      .request()
      .newBuilder()
      .addHeader(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE)
//    if (mPreferenceHelper.getUserLoginToken() != null) {
//      request.addHeader(HEADER_X_APIUSERTOKEN_KEY, mPreferenceHelper.getUserLoginToken()!!)
//    }

    val newRequest = request.build()

    // TODO Uncomment this line for slow connection simulation
    //             expensiveOperation()

    val response = chain.proceed(newRequest)
    if (response.code() == 401) {
//      mPreferenceHelper.clearUserData()
//      KNavigationHelper.openWelcomeActivity(App.context, true)
    }
    return response
  }

  /* Private Methods ******************************************************************************/

  /**
   * Simulates time-expensive operation.
   */
  private fun expensiveOperation() {
    try {
      Thread.sleep(8000)
    } catch (e: InterruptedException) {
      // this is ok
    }
  }
}