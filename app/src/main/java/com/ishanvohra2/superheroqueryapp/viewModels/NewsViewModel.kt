package com.ishanvohra2.superheroqueryapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishanvohra2.superheroqueryapp.data.NewsResponse
import com.ishanvohra2.superheroqueryapp.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    val newsUiState = MutableStateFlow<NewsUIState>(NewsUIState.LoadingState)

    private var currentPage = 1

    fun getNews(){
        viewModelScope.launch {
            NewsRepository().getNews(currentPage).run {
                if(isSuccessful && body() != null){
                    newsUiState.update {
                        when(it){
                            NewsUIState.LoadingState -> {
                                NewsUIState.SuccessState(body()!!.articles?: emptyList())
                            }
                            is NewsUIState.ErrorState -> {
                                it
                            }
                            is NewsUIState.SuccessState -> {
                                it.copy(
                                    it.items + (body()!!.articles?: emptyList())
                                )
                            }
                        }
                    }
                }
                else{
                    currentPage -= 1
                    newsUiState.emit(NewsUIState.ErrorState(null))
                }
            }
        }
    }

    fun getNextPage(){
        currentPage += 1
        getNews()
    }

    sealed class NewsUIState{
        data object LoadingState: NewsUIState()
        data class SuccessState(
            val items: List<NewsResponse.Article>
        ): NewsUIState()
        data class ErrorState(
            val message: String?
        ): NewsUIState()
    }

}