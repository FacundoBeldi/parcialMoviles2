package com.example.appservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name")
        val img = intent.getStringExtra("img")
        val status = intent.getStringExtra("status")
        val gender = intent.getStringExtra("gender")
        val location = intent.getStringExtra("location")

        val nameTextView: TextView = findViewById(R.id.etNombre)
        val imgView: ImageView = findViewById(R.id.imgPerson)
        val specieTextView: TextView = findViewById(R.id.etEstado)
        val genderTextView: TextView = findViewById(R.id.etGenero)
        val locationTextView: TextView = findViewById(R.id.etUbicacion)

        nameTextView.text = name
        specieTextView.text = status
        genderTextView.text = gender
        locationTextView.text = location
        Glide.with(this).load(img).into(imgView)
    }
}