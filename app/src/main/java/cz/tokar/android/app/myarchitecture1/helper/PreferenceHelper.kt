package cz.tokar.android.app.myarchitecture1.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.helper.extensions.*


class PreferenceHelper(context: Context) {

  private val TAG = PreferenceHelper::class.java.simpleName

  /* Private Attributes ***************************************************************************/

  /**
   * Shared preferences for current context.
   */
  private val mPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)


  /* Public Methods *******************************************************************************/

  /**
   * Determine if application runs first time.
   */
  fun getFirstRun(): Boolean {
    return mPreferences.restoreBoolean(R.string.pref_key_first_run, true)
  }

  /**
   * Sets when application has run first time.
   */
  fun setFirstRun(firstRun: Boolean) {
    mPreferences.storeBoolean(R.string.pref_key_first_run, firstRun)
  }


  fun getUserId(): Long {
    return mPreferences.restoreLong(R.string.pref_user_id, -1)
  }

  fun setUserId(userId: Long) {
    mPreferences.storeLong(R.string.pref_user_id, userId)
  }

  fun getUserPhoneNumber(): String {
    return mPreferences.restoreString(R.string.pref_user_phone_number, "") ?: ""
  }

  fun setUserPhoneNumber(phoneNumber: String) {
    mPreferences.storeString(R.string.pref_user_phone_number, phoneNumber)
  }

}