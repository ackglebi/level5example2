package com.example.level5task2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_add_game.*
import kotlinx.android.synthetic.main.game_item.view.*
import java.text.SimpleDateFormat

class GameAdapter(private var games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val formatter = SimpleDateFormat("E MMM yy HH:mm:ss z Z")
        fun bind(game: Game) {
            itemView.run {
                tvTitle.text = game.title
                tvPlatform.text = game.platform
                tvDate.text = ("Release date: " + (game.date?.day.toString()) + "-" +
                        (game.date?.month.toString()) + "-" + (game.date?.year.toString()))
            }
        }
    }
}