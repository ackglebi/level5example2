package com.example.level5task2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    // TODO get all games based on date
    @Query("SELECT * FROM gameTable ORDER BY gameTable.date DESC")
    fun getGames() : LiveData<List<Game>>

    @Insert
    fun insertGame(game: Game)

    @Delete
    suspend fun removeGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun removeAllGames()

}