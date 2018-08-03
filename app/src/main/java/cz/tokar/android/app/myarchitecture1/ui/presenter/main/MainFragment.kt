package cz.tokar.android.app.myarchitecture1.ui.presenter.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.helper.NavigationHelper
import cz.tokar.android.app.myarchitecture1.helper.extensions.dpToPx
import cz.tokar.android.app.myarchitecture1.helper.extensions.observe
import cz.tokar.android.app.myarchitecture1.ui.adapters.CommentsAdapter
import cz.tokar.android.app.myarchitecture1.ui.presenter.base.BaseFragment
import cz.tokar.android.app.myarchitecture1.ui.views.SpaceItemDecoration
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.frg_main.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

class MainFragment : BaseFragment() {

  companion object {
    val TAG = MainFragment::class.java.simpleName
    fun newInstance() = MainFragment()
  }

  private lateinit var mainViewModel: MainViewModel
  private lateinit var mAdapter: CommentsAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    Timber.v("[%s]::[onCreateView]", TAG)
    mAdapter = CommentsAdapter(activity as Context, mutableListOf()) {
      Timber.v("[%s]::[click]::[id: %d]", TAG, it.id)
      NavigationHelper.showCommentDetailFragment(activity as AppCompatActivity, R.id.container, true, it.id)
    }
    return inflater.inflate(R.layout.frg_main, container, false)
  }



  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    setupToolbar(myToolbar, R.string.toolbar_home)

    val layoutManager = LinearLayoutManager(activity)
    rvList.layoutManager = layoutManager
    rvList.adapter = mAdapter
    rvList.addItemDecoration(SpaceItemDecoration(dpToPx(R.dimen.margin_huge)))

    mainViewModel.getComments().observe(this) {
      mAdapter.setData(it.data?.toMutableList())
      swipeRefresh.isRefreshing = it.status.isLoading()
    }

    swipeRefresh.setOnRefreshListener {
      mainViewModel.refreshComments()
    }

  }

//  TODO implement this when support lib 28 is released
//  https://www.youtube.com/watch?v=pErTyQpA390&feature=youtu.be&t=5m41s
//
//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    mainViewModel.getComments().observe(viewLifeCycleOwner) {
//      // todo
//    }
//  }

  override fun onDestroyView() {
    super.onDestroyView()
    clearFindViewByIdCache()
  }
}
