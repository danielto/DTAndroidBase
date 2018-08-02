package cz.tokar.android.app.myarchitecture1.ui.adapters.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import cz.tokar.android.app.myarchitecture1.database.entity.Comment

// layout import
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_comment.*


class CommentViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

  fun bindComment(comment: Comment, listener: (Comment) -> Unit) {
    tvCommentTitle.text = comment.name
    tvCommentMessage.text = comment.body
    containerView.setOnClickListener { listener(comment) }
  }
}