package com.sopian.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sopian.github.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailGithub = intent.getParcelableExtra<Github>(EXTRA_DATA)

        with(binding){
            if (detailGithub != null){
                profileImage.setImageResource(detailGithub.avatar)
                tvName.text = detailGithub.name
                tvUserName.text = detailGithub.username
                tvCompany.text = detailGithub.company
                tvLocation.text = detailGithub.location
                tvRepositories.text = detailGithub.repository.toString()
                tvFollowing.text = detailGithub.following.toString()
                tvFollwers.text = detailGithub.follower.toString()
            }
        }
    }
}