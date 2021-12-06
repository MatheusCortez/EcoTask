package com.example.ecotask_.dependencias

import android.content.Context
import com.example.ecotask_.database.TarefaDataBase
import com.example.ecotask_.database.TarefasDao
import com.example.ecotask_.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context): TarefasDao{
        return TarefaDataBase.getDataBase(context).TarefasDao()
    }

    @Provides
    @Singleton
    fun provideTaskService(tarefasDao: TarefasDao):Repository{
        return Repository(tarefasDao)
    }
}