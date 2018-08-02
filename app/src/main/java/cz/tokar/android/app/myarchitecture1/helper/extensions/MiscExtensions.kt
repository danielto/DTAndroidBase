package cz.tokar.android.app.myarchitecture1.helper.extensions

import android.arch.lifecycle.*
import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import cz.tokar.android.app.myarchitecture1.App
import cz.tokar.android.app.myarchitecture1.network.model.base.Resource

/**
 * Gets string from resources.
 */
fun Any.str(res: Int): String {
  return App.getResString(res)
}

/**
 * Gets color from resources.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

/**
 * Converts dimension resource value to pixel size.
 */
fun Context.dpToPx(res: Int): Int = this.resources.getDimensionPixelSize(res)

fun Any.dpToPx(res: Int): Int = App.getResources()!!.getDimensionPixelSize(res)

/**
 * Converts dp size value to pixel size.
 */
fun Context.dpValueToPx(dpValue: Int): Float = (this.resources.displayMetrics.density * dpValue)


/**
 * Put String to SharedPreferences.
 */
fun SharedPreferences.storeString(res: Int, value: String?) {
  val editor = this.edit()
  editor.putString(str(res), value)
  editor.apply()
}

/**
 * Put Long to SharedPreferences.
 */
fun SharedPreferences.storeLong(res: Int, value: Long) {
  val editor = this.edit()
  editor.putLong(str(res), value)
  editor.apply()
}

/**
 * Get String to SharedPreferences.
 */
fun SharedPreferences.restoreString(res: Int, defVal: String? = null): String? {
  return this.getString(str(res), defVal)
}

/**
 * Get String to SharedPreferences.
 */
fun SharedPreferences.restoreLong(res: Int, defVal: Long = 0): Long {
  return this.getLong(str(res), defVal)
}

/**
 * Put Boolean to SharedPreferences.
 */
fun SharedPreferences.storeBoolean(res: Int, value: Boolean) {
  val editor = this.edit()
  editor.putBoolean(str(res), value)
  editor.apply()
}

/**
 * Get Boolean to SharedPreferences.
 */
fun SharedPreferences.restoreBoolean(res: Int, defVal: Boolean = false): Boolean {
  return this.getBoolean(str(res), defVal)
}

/**
 * Put Int to SharedPreferences.
 */
fun SharedPreferences.storeInt(res: Int, value: Int) {
  val editor = this.edit()
  editor.putInt(str(res), value)
  editor.apply()
}

/**
 * Get String to SharedPreferences.
 */
fun SharedPreferences.restoreInt(res: Int, defVal: Int = -1): Int {
  return this.getInt(str(res), defVal)
}

operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>?) {
  val value = this.value ?: arrayListOf()
  values?.let {
    value.addAll(it)
  }
  this.value = value
}

//Antonio's Leiva way
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
  return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
  val vm = getViewModel<T>(viewModelFactory)
  vm.body()
  return vm
}

//inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
//  return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
//}
//
//
//inline fun <reified T : ViewModel> Fragment.withViewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
//  val vm = getViewModel<T>(viewModelFactory)
//  vm.body()
//  return vm
//}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
  liveData.observe(this, Observer(body))
}

inline fun <reified T : ViewModel> Fragment.withViewModel(
  crossinline factory: () -> T,
  body: T.() -> Unit
): T {
  val vm = getViewModel(factory)
  vm.body()
  return vm
}

inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {

  val vmFactory = object : ViewModelProvider.Factory {
    override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
  }

  return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

/**
 * Syntactic sugar for [LiveData.observe] function where the [Observer] is the last parameter.
 * Hence can be passed outside the function parenthesis.
 */
inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
  this.observe(owner, Observer { it?.apply(observer) })
}

///**
// * Eliminates the boiler plate on the UI when dealing with `LiveData<Resource<T>>`
// * type from `Repository`.
// * It internally updates the [list] based upon the status and executes
// * the [f] only if status is either SUCCESS or ERROR.
// */
//fun <ResultType> Resource<ResultType>.load(list: CompleteRecyclerView, f: (ResultType?) -> Unit) {
//  list.showState(status)
//  load(f)
//}
//
///**
// * Eliminates the boiler plate on the UI when dealing with `LiveData<Resource<T>>`
// * type from `Repository`.
// * It internally executes the [f] only if status is either SUCCESS or ERROR.
// */
//fun <ResultType> Resource<ResultType>.load(f: (ResultType?) -> Unit) {
//  if (!status.isLoading()) {
//    f(data)
//  }
//}