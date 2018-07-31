package cz.tokar.android.app.myarchitecture1.ui.presenter.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import cz.tokar.android.app.myarchitecture1.core.Injectable
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.*
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {

  companion object {
    val TAG = BaseFragment::class.java.simpleName
    fun newInstance() = BaseFragment()
  }

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    configureDagger()
  }

  private fun configureDagger() {
    AndroidSupportInjection.inject(this)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    clearFindViewByIdCache()
  }
}
