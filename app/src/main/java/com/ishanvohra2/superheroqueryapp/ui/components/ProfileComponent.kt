package com.ishanvohra2.superheroqueryapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.ishanvohra2.superheroqueryapp.R
import com.ishanvohra2.superheroqueryapp.data.Superhero

class ProfileComponent(private val onBackPressed: () -> Unit) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Profile(superhero: Superhero){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBar(
                title = {
                    Text(text = "Supr")
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }){
                        Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
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
                    .size(200.dp)
                    .padding(dimensionResource(id = R.dimen.large_spacing))
                    .clip(CircleShape),
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
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = superhero.fullName,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.border_margin),
                        end = dimensionResource(id = R.dimen.border_margin),
                        bottom = dimensionResource(id = R.dimen.small_spacing)
                    )
                    .fillMaxWidth(),
                fontFamily = FontFamily.SansSerif,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = "Biography",
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.border_margin),
                        end = dimensionResource(id = R.dimen.border_margin),
                        bottom = dimensionResource(id = R.dimen.small_spacing),
                        top = dimensionResource(id = R.dimen.large_spacing)
                    )
                    .fillMaxWidth(),
                fontFamily = FontFamily.SansSerif,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${superhero.fullName}, also known by their aliases, ${superhero.aliases}, " +
                        "is a ${superhero.race} ${superhero.gender} " +
                        "superhero born in ${superhero.placeOfBirth}. Despite weighing in at " +
                        "only ${superhero.weight}, " +
                        "they possess extraordinary abilities that make them a formidable force for " +
                        "good.",
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.border_margin),
                        end = dimensionResource(id = R.dimen.border_margin),
                        bottom = dimensionResource(id = R.dimen.small_spacing)
                    )
                    .fillMaxWidth(),
                fontFamily = FontFamily.Serif,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = "Powerstats",
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.border_margin),
                        end = dimensionResource(id = R.dimen.border_margin),
                        bottom = dimensionResource(id = R.dimen.small_spacing),
                        top = dimensionResource(id = R.dimen.large_spacing)
                    )
                    .fillMaxWidth(),
                fontFamily = FontFamily.SansSerif,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.large_spacing),
                        vertical = dimensionResource(id = R.dimen.small_spacing),
                    )
            ) {
                Card(
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.small_spacing),
                        )
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_spacing))
                    ) {
                        Text(text = "Intelligence", style = MaterialTheme.typography.titleLarge)
                        Text(text = superhero.intelligence, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.small_spacing))
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_spacing))
                    ) {
                        Text(text = "Strength", style = MaterialTheme.typography.titleLarge)
                        Text(text = superhero.strength, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.large_spacing),
                        vertical = dimensionResource(id = R.dimen.small_spacing),
                    )
            ) {
                Card(
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.small_spacing),
                        )
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_spacing))
                    ) {
                        Text(text = "Speed", style = MaterialTheme.typography.titleLarge)
                        Text(text = superhero.speed, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.small_spacing))
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_spacing))
                    ) {
                        Text(text = "Durability", style = MaterialTheme.typography.titleLarge)
                        Text(text = superhero.durability, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.large_spacing),
                        vertical = dimensionResource(id = R.dimen.small_spacing),
                    )
            ) {
                Card(
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.small_spacing),
                        )
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_spacing))
                    ) {
                        Text(text = "Power", style = MaterialTheme.typography.titleLarge)
                        Text(text = superhero.power, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.small_spacing))
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Column(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_spacing))
                    ) {
                        Text(text = "Combat", style = MaterialTheme.typography.titleLarge)
                        Text(text = superhero.combat, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}