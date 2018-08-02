//package cz.tokar.android.app.myarchitecture1.ui.adapters.paging.base
//
//import android.support.v7.util.DiffUtil
//import android.support.v7.widget.RecyclerView
//import android.view.ViewGroup
//import cz.tokar.android.app.myarchitecture1.R
//
//abstract class AbstractPageListAdapter<T, VH>(comparator: DiffUtil.ItemCallback<T>, private val retryCallback: () -> Unit) : PagedListAdapter<T, VH>(comparator) where VH : RecyclerView.ViewHolder {
//  private var networkState: NetworkState? = null
//
//
//  override fun onBindViewHolder(holder: VH, position: Int) {
//    when (holder) {
//      is NetworkStateItemViewHolder -> holder.bindTo(networkState)
//    }
//  }
//
//  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH{
//    return when (viewType) {
//      R.layout.paging_network_state_item -> NetworkStateItemViewHolder.create(parent, retryCallback) as VH
//      else -> throw IllegalArgumentException("unknown view type $viewType")
//    }
//  }
//
//  private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED
//
//  internal fun isNetworkStateItem(position: Int): Boolean {
//    return hasExtraRow() && position == itemCount - 1
//  }
//
//  override fun getItemCount(): Int {
//    return super.getItemCount() + if (hasExtraRow()) 1 else 0
//  }
//
//  fun setNetworkState(newNetworkState: NetworkState?) {
//    val previousState = this.networkState
//    val hadExtraRow = hasExtraRow()
//    this.networkState = newNetworkState
//    val hasExtraRow = hasExtraRow()
//    if (hadExtraRow != hasExtraRow) {
//      if (hadExtraRow) {
//        notifyItemRemoved(super.getItemCount())
//      } else {
//        notifyItemInserted(super.getItemCount())
//      }
//    } else if (hasExtraRow && previousState != newNetworkState) {
//      notifyItemChanged(itemCount - 1)
//    }
//  }
//}