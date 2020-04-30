package com.alex.microprojectmvvm.presentation.ui.matches

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.microprojectmvvm.MicroApplication
import com.alex.microprojectmvvm.R
import com.alex.microprojectmvvm.databinding.ActivityFootballMatchesBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_football_matches.*
import javax.inject.Inject


class FootballMatchesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFootballMatchesBinding
    private lateinit var viewModel: FootballMatchesViewModel
    private var errorSnackbar: Snackbar? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MicroApplication.applicationComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_football_matches)
        binding.rvFootballMatches.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(football_matches_activity_toolbar as Toolbar)
        supportActionBar?.title = resources.getString(R.string.football_matches)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FootballMatchesViewModel::class.java).apply {
            fetchFootballMatchesData()
        }
        viewModel.errorMessage.observe(this, Observer {errorMessage ->
            if (errorMessage != null)
                showError(errorMessage)
            else
                hideError()
        })
        viewModel.footballMatchesLiveData.observe(this, Observer { footballMatches ->
            viewModel.footballMatchesAdapter.addFootballMatches(footballMatches)
        })

        binding.viewModel = viewModel
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(errorMessage: String) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }
}