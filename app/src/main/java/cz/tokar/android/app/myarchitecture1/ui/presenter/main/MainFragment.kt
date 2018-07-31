package cz.tokar.android.app.myarchitecture1.ui.presenter.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.tokar.android.app.myarchitecture1.R
import android.arch.lifecycle.ViewModelProvider
import cz.tokar.android.app.myarchitecture1.core.Injectable
import cz.tokar.android.app.myarchitecture1.ui.presenter.base.BaseFragment
import javax.inject.Inject
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.*
import timber.log.Timber

class MainFragment : BaseFragment() {

  companion object {
    val TAG = MainFragment::class.java.simpleName
    fun newInstance() = MainFragment()
  }

  private lateinit var mainViewModel: MainViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    Timber.v("[%s]::[onCreateView]", TAG)
    return inflater.inflate(R.layout.frg_main, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    clearFindViewByIdCache()
  }
}
