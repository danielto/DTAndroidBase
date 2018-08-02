package cz.tokar.android.app.myarchitecture1.core.module

import android.arch.persistence.room.Room
import android.content.Context
import cz.tokar.android.app.myarchitecture1.App
import cz.tokar.android.app.myarchitecture1.database.MyDatabase
import cz.tokar.android.app.myarchitecture1.database.dao.CommentDao
import cz.tokar.android.app.myarchitecture1.database.dao.UserDao
import cz.tokar.android.app.myarchitecture1.helper.AppExecutors
import cz.tokar.android.app.myarchitecture1.helper.PreferenceHelper
import cz.tokar.android.app.myarchitecture1.network.ApiServices
import cz.tokar.android.app.myarchitecture1.repo.CommentsRepository
import cz.tokar.android.app.myarchitecture1.repo.UserRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Base app module.
 * Application module refers to sub components and provides application level dependencies.
 */
@Module(includes = [
  ViewModelModule::class
])
class AppModule {

  @Provides
  @Singleton
  internal fun provideContext(application: App): Context {
    return application.applicationContext
  }

  @Provides
  @Singleton
  fun providePreferenceHelper(context: Context): PreferenceHelper {
    return PreferenceHelper(context)
  }

  @Provides
  @Singleton
  fun provideDatabase(application: App): MyDatabase {
    return Room.databaseBuilder(application, MyDatabase::class.java, "mydatabase.db").build()
  }

  @Provides
  @Singleton
  fun provideUserDao(db: MyDatabase): UserDao {
    return db.userDao()
  }

  @Provides
  @Singleton
  fun provideCommentDao(db: MyDatabase): CommentDao {
    return db.commentDao()
  }

  @Provides
  @Singleton
  fun provideUserRepository(dao: UserDao, retrofit: Retrofit, executors: AppExecutors ): UserRepository {
    return UserRepository(dao, retrofit, executors)
  }

  @Provides
  @Singleton
  fun provideCommentsRepository(dao: CommentDao, retrofit: Retrofit, executors: AppExecutors ): CommentsRepository {
    return CommentsRepository(dao, retrofit, executors)
  }

  @Provides
  @Singleton
  fun provideExecutors(): AppExecutors {
    return AppExecutors()
  }


}
