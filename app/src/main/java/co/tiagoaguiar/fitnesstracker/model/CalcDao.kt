package co.tiagoaguiar.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CalcDao {

    @Insert
    fun insert(Calc: Calc)

    //@Query -> buscar
    //@Update -> atualizar
    //@Delete -> excluir
}