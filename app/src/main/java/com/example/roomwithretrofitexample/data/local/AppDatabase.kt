package com.example.roomwithretrofitexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomwithretrofitexample.data.Converts
import com.example.roomwithretrofitexample.models.CryptoModel

@Database(entities = [CryptoModel::class], version = 1, exportSchema = false)
@TypeConverters(Converts::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): AppDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabaseClient(context: Context): AppDatabase {
//            if (INSTANCE != null) return INSTANCE!!
//
//            synchronized(this) {
//                INSTANCE = Room
//                    .databaseBuilder(context, AppDatabase::class.java, "Cryptos.db")
//                    .fallbackToDestructiveMigration()
//                    .build()
//
//                return INSTANCE!!
//
//            }
//        }
//
//    }

}