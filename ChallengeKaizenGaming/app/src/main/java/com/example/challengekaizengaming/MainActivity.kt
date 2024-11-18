package com.example.challengekaizengaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.challengekaizengaming.adapter.SportAdapter
import com.example.challengekaizengaming.dto.SportDTO
import com.example.challengekaizengaming.model.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerView : RecyclerView

    private lateinit var sportAdapter : SportAdapter

    private lateinit var swipeRefresh : SwipeRefreshLayout

    private lateinit var emptyTextView : TextView

    private var listSports : List<SportDTO> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listSports = createMockObjects()
        onFinishedInflate()
        getData()

    }

    private fun onFinishedInflate(){
        mainViewModel = MainViewModel()
        swipeRefresh = findViewById(R.id.swipeRefresh)
        recyclerView = findViewById(R.id.sports_recycler_view)
        emptyTextView = findViewById(R.id.emptyText)

        sportAdapter = SportAdapter(listSports)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = sportAdapter

        emptyTextView.visibility = if(listSports.isEmpty()) View.VISIBLE else View.GONE

        swipeRefresh.setOnRefreshListener {
            //listSports = createMockObjects()
            getData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun getData() {

        lifecycleScope.launch {
            try {
                mainViewModel.getSports()

                mainViewModel.sports.collect { sports ->
                    if (sports != null) {
                        listSports = sports
                        sportAdapter.setItems(listSports)
                        emptyTextView.visibility = View.GONE
                    } else {
                        emptyTextView.visibility = View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity:Error: ", e.message.orEmpty())
                Toast.makeText(this@MainActivity, "Error occur while fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun createMockObjects(): MutableList<SportDTO> {
        val jsonString = this.assets.open("sports_mock.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<SportDTO>>() {}.type
        return Gson().fromJson(jsonString, type)

    }
}
