package com.alex.microprojectmvvm.viewmodel


import com.alex.microprojectmvvm.api.service.FootballService
import com.alex.microprojectmvvm.manager.RealmDataManager
import com.alex.microprojectmvvm.model.realm.FootballMatch
import com.alex.microprojectmvvm.presentation.ui.matches.FootballMatchesViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.realm.*
import io.realm.internal.RealmCore
import io.realm.log.RealmLog
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@PowerMockRunnerDelegate(RobolectricTestRunner::class)
@Config(sdk = [21])
@PowerMockIgnore( "org.mockito.*", "org.robolectric.*", "android.*" )
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(Realm::class, RealmConfiguration::class, RealmQuery::class, RealmResults::class, RealmCore::class, RealmLog::class)
class FootballMatchesViewModelTest {

    @get:Rule
    val powerMockRule = PowerMockRule()

    private lateinit var footballApi: FootballService

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var mockRealm: Realm

    private lateinit var realmDataManager: RealmDataManager

    private lateinit var viewModel: FootballMatchesViewModel

    @Before
    fun init() {
        mockStatic(RealmCore::class.java)
        mockStatic(RealmLog::class.java)
        mockStatic(Realm::class.java)
        mockStatic(RealmConfiguration::class.java)
        Realm.init(RuntimeEnvironment.application)

        val mockRealm = mock(Realm::class.java)

        val footballMatchQuery = mockRealmQuery<FootballMatch>()
        whenever(mockRealm.where(FootballMatch::class.java)).thenReturn(footballMatchQuery)

        val footballMatchRealmResults = mockRealmResults<FootballMatch>()
        whenever(mockRealm.where(FootballMatch::class.java).findAll()).thenReturn(footballMatchRealmResults)

        compositeDisposable = mock()
        footballApi = mock()

        this.mockRealm = mockRealm
        realmDataManager = RealmDataManager(this.mockRealm)

        viewModel = FootballMatchesViewModel(footballApi, realmDataManager, compositeDisposable)
    }

    @Test
    fun loadMatches() {
        doCallRealMethod().whenever(mockRealm).executeTransaction(Mockito.any(Realm.Transaction::class.java))

        val footballMatchesList = listOf(FootballMatch(), FootballMatch(), FootballMatch())
        val FootballMatchesObservableList = Observable.just(footballMatchesList)
        whenever(footballApi.getFootballMatches()).thenReturn(FootballMatchesObservableList)

        footballApi.getFootballMatches()
        realmDataManager.saveFootballMatchesToRealm(footballMatchesList)

        verify(mockRealm, times(2)).where(FootballMatch::class.java)
        verify(footballApi, times(1)).getFootballMatches()
        verify(mockRealm, times(1)).copyToRealmOrUpdate(footballMatchesList)
    }

    private fun <T : RealmModel?> mockRealmQuery(): RealmQuery<T>? {
        @Suppress("UNCHECKED_CAST")
        return mock(RealmQuery::class.java) as RealmQuery<T>
    }

    private fun <T : RealmModel?> mockRealmResults(): RealmResults<T>? {
        @Suppress("UNCHECKED_CAST")
        return mock(RealmResults::class.java) as RealmResults<T>
    }

}