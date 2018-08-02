package cz.tokar.android.app.myarchitecture1.ui.presenter.commentdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import cz.tokar.android.app.myarchitecture1.database.entity.Comment
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.repo.CommentsRepository
import timber.log.Timber
import javax.inject.Inject

class CommentDetailViewModel @Inject constructor(
  mPreferenceHelper: PreferenceHelper,
  val repo: CommentsRepository
): ViewModel() {

  private val TAG = CommentDetailViewModel::class.java.simpleName

//  private var comment: LiveData<Comment>?

  init {
    Timber.d("[%s]::[init]::[firstRun %b]", TAG, mPreferenceHelper.getFirstRun())
  }

  fun getComment(commentId: Long): LiveData<Comment?> {
    return repo.getCommentById(commentId)
  }


}
