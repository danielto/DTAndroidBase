package cz.tokar.android.app.myarchitecture1.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "comments")
data class Comment(

  @PrimaryKey
  @SerializedName("id")
  var id: Long = 0,

  @SerializedName("postId")
  @ColumnInfo(name = "postId")
  var postId: Long = 0,

  @SerializedName("name")
  @ColumnInfo(name = "name")
  var name: String = "",

  @SerializedName("email")
  @ColumnInfo(name = "email")
  var email: String = "",

  @SerializedName("body")
  @ColumnInfo(name = "body")
  var body: String = ""
)
//{
//  constructor(): this(0, 0, "", "", "")
//}