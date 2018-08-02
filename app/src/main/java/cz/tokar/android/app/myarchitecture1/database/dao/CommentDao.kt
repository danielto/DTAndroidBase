package cz.tokar.android.app.myarchitecture1.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import cz.tokar.android.app.myarchitecture1.database.entity.User

import android.arch.persistence.room.OnConflictStrategy.REPLACE
import cz.tokar.android.app.myarchitecture1.database.entity.Comment

@Dao
interface CommentDao {

  @Insert(onConflict = REPLACE)
  fun saveComment(comment: Comment)

  @Insert(onConflict = REPLACE)
  fun saveAllComments(comment: List<Comment>)

  @Query("SELECT * FROM comments")
  fun getAllComments(): LiveData<List<Comment>>

  @Query("SELECT * FROM comments WHERE id = :id")
  fun getCommentById(id: Long): LiveData<Comment?>

  @Query("DELETE FROM comments")
  fun deleteAll()

}
