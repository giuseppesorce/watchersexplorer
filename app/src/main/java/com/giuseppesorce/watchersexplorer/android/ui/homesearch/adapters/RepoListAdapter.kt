package com.giuseppesorce.watchersexplorer.android.ui.homesearch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.giuseppesorce.commonui.bindView
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import kotlin.properties.Delegates

/**
 * @author Giuseppe Sorce
 */
class RepoListAdapter(repoList: List<Repo> = emptyList()) : RecyclerView.Adapter<RepoListAdapter.MyViewHolder>() {


    override fun getItemCount(): Int = allRepos.size

    var onActionClickListener: ((action: String, position: Int) -> Unit)? = null

    var allRepos by Delegates.observable(repoList) { _, _, _ -> notifyDataSetChanged() }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var view = holder.bindItems(allRepos[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(
            R.layout.itemlist_repocard,
            parent,
            false
        )
        return MyViewHolder(v)

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val tvLabel: TextView by bindView(R.id.tvLabel)

        fun bindItems(action: Repo): View {

            tvLabel.text = action.name
            return itemView
        }
    }

}