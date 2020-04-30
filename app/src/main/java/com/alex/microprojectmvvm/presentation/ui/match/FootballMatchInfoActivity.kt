package com.alex.microprojectmvvm.presentation.ui.match

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alex.microprojectmvvm.MicroApplication
import com.alex.microprojectmvvm.R
import com.alex.microprojectmvvm.consts.IntentVar
import com.alex.microprojectmvvm.model.FootballMatch
import com.alex.microprojectmvvm.util.MicroImage
import kotlinx.android.synthetic.main.activity_football_match_info.*
import javax.inject.Inject


class FootballMatchInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: FootballMatchInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_match_info)
        MicroApplication.applicationComponent.inject(this)

        val footballMatchName = intent.getStringExtra(IntentVar.FOOTBALL_MATCH_NAME)
        initToolbar()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FootballMatchInfoViewModel::class.java).apply {
            setTitle(footballMatchName)
            retrieveFootballMatchInfoFromAppDatabase()
        }

        viewModel.footballMatch.observe(this, Observer { footballMatch: FootballMatch? ->
            initViews(footballMatch)
        })

        team_one_link.setOnClickListener {
            startLinkInBrowser(viewModel.footballMatch.value?.side1?.url)
        }

        team_two_link.setOnClickListener {
            startLinkInBrowser(viewModel.footballMatch.value?.side2?.url)
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