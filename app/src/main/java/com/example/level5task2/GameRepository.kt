package com.example.level5task2

import android.content.Context
import androidx.lifecycle.LiveData

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGames() : LiveData<List<Game>> {
        return gameDao.getGames()
     }

    suspend fun removeGame(game: Game) {
        gameDao.removeGame(game)
    }

    fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    //TODO klopt deze? Is
    suspend fun removeAllGames() {
        return gameDao.removeAllGames()
    }


}