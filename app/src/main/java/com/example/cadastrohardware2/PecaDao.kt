package com.example.cadastrohardware2
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PecaDao {
    @Insert
    suspend fun inserir(peca: PecaHardware)

    @Query("SELECT * FROM PecaHardware")
    suspend fun listarTodas(): List<PecaHardware>

}