package cz.tokar.android.app.myarchitecture1.ui.presenter.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import cz.tokar.android.app.myarchitecture1.MainActivity
import cz.tokar.android.app.myarchitecture1.core.Injectable
import cz.tokar.android.app.myarchitecture1.helper.extensions.str
import cz.tokar.android.app.myarchitecture1.ui.presenter.history.HistoryFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
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

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        Timber.d("[%s]::[onOptionsItemSelected]::[android.R.id.home]", TAG)
        activity?.onBackPressed()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  protected fun setupToolbar(toolbar: Toolbar, title: Int? = null, showHomeAsUp: Boolean = false) {
    title?.let{
      toolbar.title = str(title)
    }
    (activity as MainActivity).setSupportActionBar(toolbar)
    (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(showHomeAsUp)
  }
}
