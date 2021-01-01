package com.sopian.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopian.github.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: GithubAdapter? = null
    private val list = ArrayList<Github>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG", getList().toString())
        list.addAll(getList())
        showRecyclerView()
    }

    private fun showRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GithubAdapter(list) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }

    fun getList(): ArrayList<Github> {
        val username = resources.getStringArray(R.array.username)
        val name = resources.getStringArray(R.array.name)
        val company = resources.getStringArray(R.array.company)
        val location = resources.getStringArray(R.array.location)
        val followers = resources.getIntArray(R.array.followers)
        val following = resources.getIntArray(R.array.following)
        val avatar = resources.obtainTypedArray(R.array.avatar)
        val repository = resources.getIntArray(R.array.repository)

        val listGithub = ArrayList<Github>()
        for (pos in username.indices) {
            val data = Github(
                username[pos],
                name[pos],
                avatar.getResourceId(pos, -1),
                company[pos],
                location[pos],
                repository[pos],
                followers[pos],
                following[pos]
            )
            listGithub.add(data)
        }

        return listGithub
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("MainActivity", newText.toString())
                adapter?.filter?.filter(newText)
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}