package com.example.level5task2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    var games: LiveData<List<Game>> = gameRepository.getGames()
    private val ioScope = CoroutineScope(Dispatchers.IO)

    // todo add coroutines?
    fun insertGame(game: Game) {
//        Log.i("INSERTING GAME", game.year.toString())
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game) {
         ioScope.launch {
             gameRepository.removeGame(game)
         }
    }

    fun removeAllGames() {
        ioScope.launch {
            gameRepository.removeAllGames()
        }
    }

}