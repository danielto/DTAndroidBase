package cz.tokar.android.app.myarchitecture1.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import cz.tokar.android.app.myarchitecture1.database.dao.UserDao
import cz.tokar.android.app.myarchitecture1.database.entity.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class MyDatabase : RoomDatabase() {

  // --- DAO ---
  abstract fun userDao(): UserDao

  companion object {

    // --- SINGLETON ---
    private var INSTANCE: MyDatabase? = null

    fun getInstance(context: Context): MyDatabase {
      if (INSTANCE == null) {
        synchronized(MyDatabase::class) {
          INSTANCE = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "mydatabase.db").build()
        }
      }
      return INSTANCE!!
    }

    fun destroyIntance() {
      INSTANCE = null
    }
  }
}
