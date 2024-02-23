package com.ishanvohra2.superheroqueryapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.ishanvohra2.superheroqueryapp.R
import com.ishanvohra2.superheroqueryapp.data.Superhero
import com.ishanvohra2.superheroqueryapp.viewModels.HomeViewModel

class HomeComponent(
    private val onClicked: (s: Superhero) -> Unit
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomePage(homeViewModel: HomeViewModel = viewModel()){
        homeViewModel.getRecentlyViewedData()
        Column {
            TopAppBar(title = {
                Text(text = "Supr")
            })
            var searchText by remember {
                mutableStateOf(TextFieldValue(""))
            }
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    if(it.text.isNotBlank()){ homeViewModel.searchSuperhero(it.text) }
                    else {
                        homeViewModel.updateSearchState(HomeViewModel.SearchUiState.IdleState)
                    }
                },
                label = {
                    Text(text = "Search here...")
                },
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.small_spacing),
                        start = dimensionResource(id = R.dimen.border_margin),
                        end = dimensionResource(id = R.dimen.border_margin),
                        bottom = dimensionResource(id = R.dimen.small_spacing)
                    )
                    .fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.small_spacing)),
                    )
                }
            )
            SearchState(homeViewModel)
            RecentlyViewed(homeViewModel)
        }
    }

    @Composable
    fun SearchState(viewModel: HomeViewModel){
        when(val state =
            viewModel.searchData.collectAsState().value){
            is HomeViewModel.SearchUiState.IdleState -> {}
            is HomeViewModel.SearchUiState.ErrorState -> {}
            HomeViewModel.SearchUiState.LoadingState -> {
                LoadingState()
            }
            is HomeViewModel.SearchUiState.SuccessState -> {
                if(state.list.isNotEmpty()){
                    val listState = rememberLazyListState()
                    Column{
                        LazyColumn(
                            state = listState,
                            content = {
                                items(state.list.size) {
                                    SuperheroCard(superhero = state.list[it]){ s->
                                        viewModel.saveSuperheroData(s)
                                        onClicked(s)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun RecentlyViewed(viewModel: HomeViewModel) {
        when(val state =
            viewModel.recentlyViewed.collectAsState().value){
            is HomeViewModel.RecentlyViewedUiState.ErrorState -> {}
            HomeViewModel.RecentlyViewedUiState.LoadingState -> {
//                LoadingState()
            }
            is HomeViewModel.RecentlyViewedUiState.SuccessState -> {
                if(state.list.isNotEmpty()){
                    val listState = rememberLazyListState()
                    Column{
                        LazyColumn(
                            state = listState,
                            content = {
                                item{
                                    Text(
                                        text = "Recently viewed",
                                        modifier = Modifier
                                            .padding(
                                                start = dimensionResource(id = R.dimen.border_margin),
                                                end = dimensionResource(id = R.dimen.border_margin),
                                                bottom = dimensionResource(id = R.dimen.small_spacing)
                                            ),
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = TextUnit(24f, TextUnitType.Sp)
                                    )
                                }
                                items(state.list.size) {
                                    SuperheroCard(superhero = state.list[it]){ s->
                                        viewModel.saveSuperheroData(s)
                                        onClicked(s)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun SuperheroCard(superhero: Superhero, onClicked: (s: Superhero) -> Unit){
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.large_spacing),
                    vertical = dimensionResource(id = R.dimen.medium_spacing),
                )
                .fillMaxWidth()
                .clickable { onClicked(superhero) },
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .data(superhero.imageUrl)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_radius)))
                    .zIndex(10f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.name,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.border_margin),
                        end = dimensionResource(id = R.dimen.border_margin),
                        bottom = dimensionResource(id = R.dimen.small_spacing)
                    )
                    .fillMaxWidth(),
                fontFamily = FontFamily.SansSerif,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                textAlign = TextAlign.Center
            )
        }
    }

}