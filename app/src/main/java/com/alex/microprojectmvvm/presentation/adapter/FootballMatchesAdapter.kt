package com.alex.microprojectmvvm.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.microprojectmvvm.R
import com.alex.microprojectmvvm.consts.IntentVar
import com.alex.microprojectmvvm.model.realm.FootballMatch
import com.alex.microprojectmvvm.presentation.ui.match.FootballMatchInfoActivity
import com.alex.microprojectmvvm.util.MicroImage
import io.realm.OrderedRealmCollection
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_football_match_item.*

class FootballMatchesAdapter(private var itemList: OrderedRealmCollection<FootballMatch>)
    : RecyclerView.Adapter<FootballMatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_football_match_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(footballMatch: FootballMatch) {
            match_title.text = footballMatch.title
            MicroImage.loadImageFromUrl(containerView.context, footballMatch.thumbnail, match_image)

            containerView.setOnClickListener {
                startFootballMatchInfoActivity(footballMatch.title)
            }
        }

        private fun startFootballMatchInfoActivity(footballMatchName: String) {
            val intent = Intent(containerView.context, FootballMatchInfoActivity::class.java)
            intent.putExtra(IntentVar.FOOTBALL_MATCH_NAME, footballMatchName)
            containerView.context.startActivity(intent)
        }
    }
}