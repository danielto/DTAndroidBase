package cz.tokar.android.app.myarchitecture1.ui.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.view.View

/**
 * Item decoration with equal space between items.
 * Designed for LinearLayoutManager.
 */
class SpaceItemDecoration(spaceInPx: Int) : ItemDecoration() {
    private val mHalfSpace = spaceInPx / 2

    /**
     * @see ItemDecoration.getItemOffsets
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.top = mHalfSpace
        outRect.bottom = mHalfSpace
    }
}
