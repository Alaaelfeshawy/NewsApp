package com.example.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.repository.PrefsRepository
import com.example.data.source.local.room.AppDatabase
import com.example.data.source.local.room.dao.article.ArticleDao
import com.example.data.source.local.shared_prefs.AppPreferencesHelper
import com.example.data.source.local.shared_prefs.PreferencesHelper
import com.example.domain.repository.IPrefsRepository
import com.example.domain.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    @Provides
    @Named("db_name")
    fun providerAppDatabaseName(): String {
        return AppConstants.DB_NAME
    }


    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @Named("db_name") name: String?
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, name!!
        )
            .fallbackToDestructiveMigration()
            .addCallback(dbCallback)
            .build()
    }

    private val dbCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
        }
    }

    @Provides
    @Named("pref_name")
    fun providerSharedPrefName(): String {
        return AppConstants.SHARED_PREFERENCE
    }
    @Provides
    @Singleton
    fun providerAppPrefs(
        @ApplicationContext context: Context?,
        @Named("pref_name") name: String?
    ): PreferencesHelper {
        return AppPreferencesHelper(context!!, name)
    }
    @Singleton
    @Provides
    fun provideComboDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.articleDao()
    }

    @Singleton
    @Provides
    fun provideSharedPref(prefsRepository: PrefsRepository): IPrefsRepository {
        return prefsRepository
    }
}