package cz.tokar.android.app.myarchitecture1.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.database.entity.Comment
import cz.tokar.android.app.myarchitecture1.ui.adapters.base.BaseAdapter
import cz.tokar.android.app.myarchitecture1.ui.adapters.viewholder.CommentViewHolder

class CommentsAdapter(mContext: Context, mList: MutableList<Comment>, val mListener: (Comment) -> Unit): BaseAdapter<Comment>(mContext, mList) {

  init {
    setHasStableIds(true)
  }

  override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_comment, parent, false)
    return CommentViewHolder(view)
  }

  override fun onBindData(holder: RecyclerView.ViewHolder, comment: Comment, position: Int) {
    when (holder) {
      is CommentViewHolder -> setupComment(holder, comment)
    }
  }

  override fun onItemViewType(pos: Int): Int {
    return 0
  }


  override fun countItems(): Int {
    return mList.size
  }

  override fun getItemId(position: Int): Long {
    return mList[position].id
  }

  // ******************************************************************************************** //

  private fun setupComment(holder: CommentViewHolder, comment: Comment?) {
    comment?.let {
      holder.bindComment(it, mListener)
    }
  }
}