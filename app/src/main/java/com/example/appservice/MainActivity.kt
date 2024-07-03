package com.example.appservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var pjList = mutableListOf<Results>() //lista mutable para utilizar en el adapter, donde guardo los pjs de la API
    private lateinit var mediaPlayer: MediaPlayer

    val job = Job() //nose que hace

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getCharacters() //funcion que consume la API
        adapter = Adapter(this@MainActivity,pjList) //agrego al adapter la info del personaje para manejarla
        recyclerView.adapter = adapter

        mediaPlayer = MediaPlayer.create(this, R.raw.rickandmorty)
        mediaPlayer.isLooping = true
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer.start() // Iniciar la música cuando la actividad se inicia
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.pause() // Pausar la música cuando la actividad se detiene
    }

    private fun getCharacters() { //llamo a la API y guardo la informacion en una lista
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getListOfSpecies("character/?species=Alien")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val charactersMap = response?.results
                    pjList.clear()
                    if (charactersMap != null) {
                        charactersMap.map {
                            pjList.add(it)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_FACU)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val URL_FACU = "https://rickandmortyapi.com/api/"
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        job.cancel()
    }
}


