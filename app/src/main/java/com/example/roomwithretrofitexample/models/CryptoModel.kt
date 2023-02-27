package com.example.roomwithretrofitexample.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cryptosData")
data class CryptoModel(
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price_usd: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int
) : Serializable


