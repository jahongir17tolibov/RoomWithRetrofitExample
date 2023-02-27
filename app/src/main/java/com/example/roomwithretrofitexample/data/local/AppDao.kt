package com.example.roomwithretrofitexample.data.local

import androidx.room.*
import com.example.roomwithretrofitexample.models.CryptoModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<CryptoModel>)

    @Query("SELECT * FROM cryptosData")
    fun getAllData(): List<CryptoModel>

    @Delete
    fun deleteData(data: List<CryptoModel>)

}