package com.alex.microprojectmvvm.presentation.ui.match

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alex.microprojectmvvm.MicroApplication
import com.alex.microprojectmvvm.R
import com.alex.microprojectmvvm.consts.IntentVar
import com.alex.microprojectmvvm.manager.RealmDataManager
import com.alex.microprojectmvvm.model.realm.FootballMatch
import com.alex.microprojectmvvm.util.MicroImage
import kotlinx.android.synthetic.main.activity_football_match_info.*
import javax.inject.Inject

class FootballMatchInfoActivity : AppCompatActivity() {

    private var footballMatch: FootballMatch? = null

    @Inject
    lateinit var realmDataManager: RealmDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_info)
        MicroApplication.applicationComponent.inject(this)

        val footballMatchName = intent.getStringExtra(IntentVar.FOOTBALL_MATCH_NAME)
        initToolbar()
        footballMatch = realmDataManager.getFootballMatch(footballMatchName)

        initViews(footballMatch)

        team_one_link.setOnClickListener {
            startLinkInBrowser(footballMatch?.side1?.url)
        }

        team_two_link.setOnClickListener {
            startLinkInBrowser(footballMatch?.side2?.url)
        }
    }

    private fun initViews(footballMatch: FootballMatch?) {
        if (footballMatch == null) return

        football_match_name.text = footballMatch.title
        football_competition_name.text = footballMatch.competition?.name
        football_date.text = footballMatch.date.toString()
        MicroImage.loadImageFromUrl(this, footballMatch.thumbnail, football_match_thumbnail)
        team_one.text = "Live Stream of ${footballMatch.side1?.name}"
        team_two.text = "Live Stream of ${footballMatch.side2?.name}"
        team_one_link.text = footballMatch.side1?.url
        team_two_link.text = footballMatch.side2?.url
    }

    private fun startLinkInBrowser(link: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

    private fun initToolbar() {
        setSupportActionBar(football_match_info_activity_toolbar as Toolbar)
        supportActionBar?.title = resources.getString(R.string.football_match_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}