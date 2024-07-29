package com.hawwas.batterymanager.di

import android.app.*
import android.content.*
import androidx.room.*
import com.hawwas.batterymanager.data.database.*
import com.hawwas.batterymanager.data.database.dao.*
import com.hawwas.batterymanager.data.repo.*
import com.hawwas.batterymanager.domain.repo.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
     fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, databaseName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideLocalRepo(appDatabase: AppDatabase): LocalRepo {
        return LocalRepoImpl(appDatabase)
    }
}