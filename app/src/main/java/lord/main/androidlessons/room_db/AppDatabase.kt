package lord.main.androidlessons.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TableChannels::class, TableHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun channelsDao(): TableChannelsDao
    abstract fun historyDao(): TableHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db_name.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}