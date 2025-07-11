package com.example.cadastrohardware2
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class PecaHardware(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String,
    val marca: String,
    val capacidade: String,
    val modelo: String
)
