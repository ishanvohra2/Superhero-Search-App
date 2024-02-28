package com.ishanvohra2.superheroqueryapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ishanvohra2.superheroqueryapp.R
import com.ishanvohra2.superheroqueryapp.data.NewsResponse
import com.ishanvohra2.superheroqueryapp.viewModels.NewsViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class NewsComponent {

    @Composable
    fun NewsPage(vM: NewsViewModel = viewModel()){
        vM.getNews()
        NewsList(vM)
    }

    @Composable
    private fun NewsList(vM: NewsViewModel){
        when(val state = vM.newsUiState.collectAsState().value){
            is NewsViewModel.NewsUIState.ErrorState -> {
                /*Show error*/
            }
            NewsViewModel.NewsUIState.LoadingState -> {
                LoadingState()
            }
            is NewsViewModel.NewsUIState.SuccessState -> {
                val listState = rememberLazyListState()
                LazyColumn(state = listState){
                    items(state.items.count()){ i ->
                        NewsListItem(news = state.items[i])
                    }
                }
                LaunchedEffect(key1 = listState.canScrollForward, block = {
                    if(!listState.canScrollForward){
                        vM.getNextPage()
                    }
                })
            }
        }
    }

    @Composable
    private fun NewsListItem(news: NewsResponse.Article){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.large_spacing),
                    vertical = dimensionResource(id = R.dimen.medium_spacing)
                )
        ){
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(news.urlToImage)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .clip(
                        RoundedCornerShape(
                            20.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.large_spacing),
                            end = dimensionResource(id = R.dimen.large_spacing),
                            bottom = dimensionResource(id = R.dimen.small_spacing)
                        ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Text(
                    text = news.source.name,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.large_spacing),
                            end = dimensionResource(id = R.dimen.large_spacing)
                        )
                )
                val date = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).parse(news.publishedAt)
                Text(
                    text = date?.let {
                        SimpleDateFormat(
                            "dd MMM",
                            Locale.getDefault()
                        ).format(it)
                    }?:"",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.large_spacing),
                            end = dimensionResource(id = R.dimen.large_spacing)
                        )
                )
            }
        }
    }

}