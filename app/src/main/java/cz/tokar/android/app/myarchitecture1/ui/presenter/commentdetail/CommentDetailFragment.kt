package cz.tokar.android.app.myarchitecture1.ui.presenter.commentdetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.helper.extensions.observe
import cz.tokar.android.app.myarchitecture1.ui.presenter.base.BaseFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.frg_comment_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

class CommentDetailFragment : BaseFragment() {

  companion object {
    const val ARGS_COMMENT_ID = "args_comment_id"
    val TAG = CommentDetailFragment::class.java.simpleName

    fun newInstance(commentId: Long): CommentDetailFragment {
      return CommentDetailFragment().apply {
        arguments = Bundle().apply {
          putLong(ARGS_COMMENT_ID, commentId)
        }
      }
    }
  }

  private lateinit var mainViewModel: CommentDetailViewModel

  private val mCommentId: Long by lazy { arguments?.getLong(ARGS_COMMENT_ID) ?: 0 }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    Timber.v("[%s]::[onCreateView]", TAG)
    setHasOptionsMenu(true)

    return inflater.inflate(R.layout.frg_comment_detail, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentDetailViewModel::class.java)

    setupToolbar(myToolbar, R.string.toolbar_comment_detail, true)

    setCommentView()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    clearFindViewByIdCache()
  }

  private fun setCommentView(){
    mainViewModel.getComment(mCommentId).observe(this) {
      it?.let{
        Timber.d("[%s]::[comment.id: %d]", TAG, it.id)
        tvCommentTitle.text = it.name
        tvCommentMessage.text = it.body
        tvEmail.text = it.email
      }
    }
  }
}
