package developer.abdulahad.mydictionary.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import developer.abdulahad.mydictionary.Room.Dao.MyDao
import developer.abdulahad.mydictionary.Room.Entity.MyRoom

@Database(entities = [MyRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): MyDao

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context, AppDatabase::class.java, "user_data")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return appDatabase!!
        }
    }
}