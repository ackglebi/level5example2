package com.example.level5task2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val GAME = "GAME"

class AddGameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener {
            onSaveClick()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun onSaveClick() {
        val firstDate = (etDay.text.toString()) + "-" + (etMonth.text.toString()) + "-" + (etYear.text.toString())
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = formatter.parse(firstDate)

        if (checkFields()) {
            val addGame = Game(
                title = etTitle.text.toString(),
                platform = etTitle.text.toString(),
                date = date
            )

            val resultIntent = Intent()
            resultIntent.putExtra(GAME, addGame)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "FILL IN ALL FIELDs", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkFields() : Boolean {
        return (etTitle.text.toString().isNotBlank() || etPlatform.text.toString().isNotBlank() || etDay.text.toString().isNotBlank()
            || etMonth.text.toString().isNotBlank() || etYear.text.toString().isNotBlank())
    }

}
