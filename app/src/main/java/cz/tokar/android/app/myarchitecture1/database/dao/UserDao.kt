package cz.tokar.android.app.myarchitecture1.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import cz.tokar.android.app.myarchitecture1.database.entity.User

import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface UserDao {

  @Insert(onConflict = REPLACE)
  fun save(user: User)

  @Insert(onConflict = REPLACE)
  fun saveAll(user: List<User>)

  @Query("SELECT * FROM users")
  fun getAllUsers(): LiveData<List<User>>

  @Query("SELECT * FROM users WHERE id = :id")
  fun getUserById(id: Long): LiveData<User>


}
