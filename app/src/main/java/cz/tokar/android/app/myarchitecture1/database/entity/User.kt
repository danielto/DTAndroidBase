package cz.tokar.android.app.myarchitecture1.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(

  @PrimaryKey
  @SerializedName("id")
  var id: Long?,

  @SerializedName("userName")
  @ColumnInfo(name = "userName")
  var userName: String = "",

  @SerializedName("birthDate")
  @ColumnInfo(name = "birthDate")
  var birthDate: String = ""
) {
  constructor(): this(null, "", "")
}