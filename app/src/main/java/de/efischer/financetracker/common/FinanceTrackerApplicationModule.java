package de.efischer.financetracker.common;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)

public class FinanceTrackerApplicationModule {

    @Provides
    @Singleton
    public ApplicationDatabase database(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, ApplicationDatabase.class, "database").build();
    }
}
