package de.efischer.financetracker.common.configuration;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import de.efischer.financetracker.common.ApplicationDatabase;

@Module
@InstallIn(SingletonComponent.class)

public class ApplicationConfiguration {

    @Provides
    @Singleton
    public ApplicationDatabase database(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "database").fallbackToDestructiveMigration().build();
    }
}
