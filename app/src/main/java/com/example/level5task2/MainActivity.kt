package com.example.level5task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private var games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        observeViewModel()
    }

    /**
     * observes the list of games and checks for changes
     */
    private fun observeViewModel() {
        mainActivityViewModel.games.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            Log.i("size: ", "${gameAdapter.itemCount}")
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)
        fab.setOnClickListener {
            val intent = Intent(this, AddGameActivity::class.java)
            startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("onActivityResult", "")

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    data?.let { safeData ->
                        val game = safeData.getParcelableExtra<Game>(GAME)

//                        Log.i("onActivityResult", game.year.toString())
                        game?.let {
                            mainActivityViewModel.insertGame(game)
                        } ?: run {
                            Log.e("TAG", "Reminder is null")
                        }
                    } ?: run {
                        Log.e("TAG", "null intent data received")
                    }
                }
            }
        }
    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                mainActivityViewModel.deleteGame(gameToDelete)
            }
        }

        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                mainActivityViewModel.removeAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val ADD_GAME_REQUEST_CODE = 100
    }
}
