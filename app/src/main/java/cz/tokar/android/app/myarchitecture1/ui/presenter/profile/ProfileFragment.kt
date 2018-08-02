package cz.tokar.android.app.myarchitecture1.ui.presenter.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import cz.tokar.android.app.myarchitecture1.MainActivity
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.helper.NavigationHelper
import cz.tokar.android.app.myarchitecture1.ui.presenter.base.BaseFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainFragment
import kotlinx.android.synthetic.main.frg_profile.*
import timber.log.Timber

class ProfileFragment : BaseFragment() {

  companion object {
    val TAG = ProfileFragment::class.java.simpleName
    fun newInstance() = ProfileFragment()
  }

  private lateinit var viewModel: ProfileViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View {
    Timber.v("[%s]::[onCreateView]", MainFragment.TAG)
    val view = inflater.inflate(R.layout.frg_profile, container, false)
    setHasOptionsMenu(true)
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    (activity as MainActivity).setSupportActionBar(myToolbar)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
    viewModel.getUserPhoneNumber().observe(this, Observer {
      setToolbarTitle(it)
    })

    viewModel.getUserEmail().observe(this, Observer {
      tvEmailWaiting.text = getString(R.string.email_waiting_message, it)
    })

    btnHistory.setOnClickListener{
      NavigationHelper.showHistoryFragment(activity as MainActivity, R.id.container, true)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_profile, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  /* Private methods ******************************/
  private fun setToolbarTitle(title: String?) {
    // todo set ion-android-person icon to title
    collapsingToolbar.title = title
  }

}
