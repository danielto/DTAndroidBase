package cz.tokar.android.app.myarchitecture1.ui.adapters.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * General base adapter.
 */
abstract class BaseAdapter<T>(protected var mContext: Context, protected var mList: MutableList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  /* Abstract Methods *****************************************************************************/

  abstract fun createCustomViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
  abstract fun onBindData(holder: RecyclerView.ViewHolder, value: T, position: Int)
  abstract fun onItemViewType(pos: Int): Int
  abstract fun countItems(): Int

  /* Public Methods *******************************************************************************/

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val holder = createCustomViewHolder(parent, viewType)
    return holder
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    onBindData(holder, mList[position], position)
  }

  override fun getItemViewType(position: Int): Int {
    return onItemViewType(position)
  }

  override fun getItemCount(): Int {
    return countItems()
  }

  fun setData(list: MutableList<T>?) {
    mList.clear()
    list?.let{
      mList.addAll(it)
    }
    this.notifyDataSetChanged()
  }

  fun clearData() {
    mList.clear()
    this.notifyDataSetChanged()
  }

  open fun addItems(items: MutableList<T>) {
    mList = items
    this.notifyDataSetChanged()
  }

  fun removeItem(itemToRemove: T) {
    val itemToRemovePos = mList.indexOf(itemToRemove)
    mList.remove(itemToRemove)
    this.notifyItemRemoved(itemToRemovePos)
  }

  fun getItem(position: Int): T {
    return mList[position]
  }

  fun reportError(): Nothing {
    throw RuntimeException()
  }

  /* Private Methods ******************************************************************************/
  /* Getters / Setters ****************************************************************************/
  /* Inner classes ********************************************************************************/

}