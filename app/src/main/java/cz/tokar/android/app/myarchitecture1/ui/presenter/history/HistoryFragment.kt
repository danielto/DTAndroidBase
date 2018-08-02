package cz.tokar.android.app.myarchitecture1.ui.presenter.history

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import cz.tokar.android.app.myarchitecture1.MainActivity
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.ui.presenter.base.BaseFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber


class HistoryFragment : BaseFragment() {

  companion object {
    val TAG = HistoryFragment::class.java.simpleName
    fun newInstance() = HistoryFragment()
  }

  private lateinit var viewModel: HistoryViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    Timber.v("[%s]::[onCreateView]", TAG)
    val view = inflater.inflate(R.layout.frg_history, container, false)
    setHasOptionsMenu(true)
    return view
  }


  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)

    setupToolbar(myToolbar, R.string.toolbar_history, true)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    clearFindViewByIdCache()
  }


}
