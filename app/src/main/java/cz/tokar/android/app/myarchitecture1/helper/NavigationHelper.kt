package cz.tokar.android.app.myarchitecture1.helper

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.ui.presenter.commentdetail.CommentDetailFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.favorite.FavoriteFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.history.HistoryFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.profile.ProfileFragment
import timber.log.Timber


/**
 * Replaces fragment in certain container.
 */

/**
 * Navigation helper class.
 */
class NavigationHelper {

  companion object {


    @JvmStatic
    fun showHomeFragment(activity: AppCompatActivity, containerId: Int, addToBackStack: Boolean){
      clearFragmentBackStack(activity)
      val fragment = MainFragment.newInstance()
      val transaction = beginTransaction(activity)
      replaceFragment(transaction, containerId, fragment, addToBackStack)
    }


    @JvmStatic
    fun showProfileFragment(activity: AppCompatActivity, containerId: Int, addToBackStack: Boolean){
      clearFragmentBackStack(activity)
      val fragment = ProfileFragment.newInstance()
      val transaction = beginTransaction(activity)
      replaceFragment(transaction, containerId, fragment, addToBackStack)
    }

    @JvmStatic
    fun showFavoriteFragment(activity: AppCompatActivity, containerId: Int, addToBackStack: Boolean){
      clearFragmentBackStack(activity)
      val fragment = FavoriteFragment.newInstance()
      val transaction = beginTransaction(activity)
      replaceFragment(transaction, containerId, fragment, addToBackStack)
    }

    @JvmStatic
    fun showHistoryFragment(activity: AppCompatActivity, containerId: Int, addToBackStack: Boolean){
      val fragment = HistoryFragment.newInstance()
      val transaction = beginTransaction(activity)
      replaceFragment(transaction, containerId, fragment, addToBackStack)
    }

    @JvmStatic
    fun showCommentDetailFragment(activity: AppCompatActivity, containerId: Int, addToBackStack: Boolean, commentId: Long){
      val fragment = CommentDetailFragment.newInstance(commentId)
      val transaction = beginTransaction(activity)
      replaceFragment(transaction, containerId, fragment, addToBackStack)
    }

    private fun replaceFragment(transaction: FragmentTransaction, containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
      replaceFragment(transaction, containerId, fragment, addToBackStack, -1, -1)
    }

    /**
     * Replaces fragment in certain container.
     */
    private fun replaceFragment(transaction: FragmentTransaction, containerId: Int, fragment: Fragment, addToBackStack: Boolean, enterAnim: Int, exitAnim: Int) {
      Timber.d("replaceFragment(): addToBackStack: " + addToBackStack)

      // Set transaction animations if passed.
      if (enterAnim >= 0 && exitAnim >= 0) {
        transaction.setCustomAnimations(enterAnim, R.anim.no_anim, R.anim.no_anim, exitAnim)
      }

      // Replace fragment in container.
      transaction.replace(containerId, fragment)

      // Add to back-stack.
      if (addToBackStack) {
        transaction.addToBackStack(null)
      }

      transaction.commitAllowingStateLoss()
    }

    /**
     * Commit fragment in activity.
     *
     * @param activity Parent activity.
     *
     * @return Fragment transaction.
     */
    @SuppressLint("CommitTransaction")
    private fun beginTransaction(activity: FragmentActivity): FragmentTransaction {
      return activity.supportFragmentManager.beginTransaction()
    }

    /**
     * Commit fragment nested in other fragment.
     *
     * @param fragment Parent activity.
     *
     * @return Fragment transaction.
     */
    @SuppressLint("CommitTransaction")
    private fun beginTransaction(fragment: Fragment): FragmentTransaction {
      return fragment.fragmentManager!!.beginTransaction()
    }

    private fun clearFragmentBackStack(activity: AppCompatActivity) {
      activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
  }



}