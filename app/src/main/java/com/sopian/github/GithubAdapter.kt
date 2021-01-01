package com.sopian.github

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sopian.github.databinding.ItemGithubBinding
import java.util.*
import kotlin.collections.ArrayList

class GithubAdapter(
    private var githubList: MutableList<Github>,
    private var onClick: (Github) -> Unit
) : RecyclerView.Adapter<GithubAdapter.ViewHolder>(), Filterable{

    var githubFilterList = ArrayList<Github>()

    init {
        githubFilterList = githubList as ArrayList<Github>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGithubBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(githubFilterList[position])
    }

    override fun getItemCount(): Int = githubFilterList.size

    inner class ViewHolder(private val binding: ItemGithubBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(github: Github){

            with(binding){
                profileImage.setImageResource(github.avatar)
                tvName.text = github.name
                tvCompany.text = github.company
                tvLocation.text = github.location
                root.setOnClickListener {
                    onClick(github)
                }
            }
        }
    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charSearch = constraint.toString()
                if (charSearch.isEmpty()){
                    githubFilterList = githubList as ArrayList<Github>
                }else{
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    val resultList = ArrayList<Github>()

                    for (item in githubList) {
                        if (item.name.toLowerCase(Locale.ROOT).contains(filterPattern)){
                            resultList.add(item)
                        }
                    }
                    githubFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = githubFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                githubFilterList = results?.values as ArrayList<Github>
                notifyDataSetChanged()
            }

        }
    }



}