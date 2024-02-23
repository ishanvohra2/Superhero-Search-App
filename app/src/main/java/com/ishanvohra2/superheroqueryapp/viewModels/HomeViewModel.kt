package com.ishanvohra2.superheroqueryapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishanvohra2.superheroqueryapp.dao.SearchDao
import com.ishanvohra2.superheroqueryapp.data.Superhero
import com.ishanvohra2.superheroqueryapp.repository.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel: ViewModel() {

    private val dao by inject<SearchDao>(SearchDao::class.java)

    val recentlyViewed = MutableStateFlow<RecentlyViewedUiState>(
        RecentlyViewedUiState.LoadingState
    )

    val searchData = MutableStateFlow<SearchUiState>(
        SearchUiState.IdleState
    )

    private var searchJob: Job? = null

    fun updateSearchState(state: SearchUiState){
        viewModelScope.launch {
            searchData.emit(state)
        }
    }

    fun saveSuperheroData(superhero: Superhero){
        CoroutineScope(Dispatchers.IO).launch {
            if(dao.getSuperheroById(superhero.id) == 0){
                dao.insertSearchData(superhero)
                getRecentlyViewedData()
            }
        }
    }

    fun searchSuperhero(name: String){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(2000)
            SearchRepository().searchSuperhero(name).run {
                if(isSuccessful && body() != null){
                    searchData.emit(SearchUiState.SuccessState(body()!!.toSuperhero()))
                }
                else{
                    searchData.emit(SearchUiState.ErrorState(null))
                }
            }
        }
    }

    fun getRecentlyViewedData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dao.getSearchData()?.let {
                    recentlyViewed.emit(RecentlyViewedUiState.SuccessState(it))
                }
            }
        }
    }

    sealed class RecentlyViewedUiState{
        data class ErrorState(val message: String?): RecentlyViewedUiState()
        data class SuccessState(val list: List<Superhero>): RecentlyViewedUiState()
        data object LoadingState: RecentlyViewedUiState()
    }

    sealed class SearchUiState{
        data class ErrorState(val message: String?): SearchUiState()
        data class SuccessState(val list: List<Superhero>): SearchUiState()
        data object LoadingState: SearchUiState()
        data object IdleState: SearchUiState()
    }

}