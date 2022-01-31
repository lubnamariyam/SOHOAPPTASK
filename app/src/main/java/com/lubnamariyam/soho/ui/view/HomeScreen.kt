package com.lubnamariyam.soho.ui.view

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.lubnamariyam.soho.model.Result
import com.lubnamariyam.soho.ui.theme.LightGrey
import com.lubnamariyam.soho.ui.theme.SohoTheme
import com.lubnamariyam.soho.ui.view.ProfileScreen.Companion.resultData
import com.lubnamariyam.soho.ui.view.SearchScreen.Companion.randomUserResponseData

@ExperimentalFoundationApi
@Composable
fun ProductListScreen(results: List<Result>, navController: NavController, activity: Activity) {
    Column() {
        TopAppBar(
            title = {
                Row() {
                    Text(
                        text = "Soho",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif, color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    WeatherState()
                }
            },
        )
        SearchBar(
            hint = "Search...",
            modifier = Modifier
                .clickable { navController.navigate("search_screen") }
                .fillMaxWidth()
                .padding(12.dp)
        )
        randomUserResponseData = results
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(results.size) { index ->
                ProfileCard(result = results[index], navController)
            }
        }

    }
    BackHandler() {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Exit App")
        alertDialogBuilder.setMessage("Are you sure you want to exit?")
        alertDialogBuilder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            activity.finish()
        }
        alertDialogBuilder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })

        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }
}

@Composable
fun ProfileCard(result: Result, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(220.dp)
            .background(Color.White)
            .clickable {
                resultData = result
                navController.navigate("profile_description")
            },
        shape = RoundedCornerShape(8.dp), elevation = 6.dp,
    ) {
        Surface(
            modifier = Modifier.background(Color.White)
        ) {
            Column(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = result.picture.large,
                        builder = {
                            scale(Scale.FIT)
                            placeholder(R.drawable.notification_action_background)
                            transformations()
                        }
                    ),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.2f)
                )
                Text(
                    text = "${result.name.first}  ${result.name.last}",
                    modifier = Modifier.padding(start = 4.dp),
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.padding(4.dp))

            }


        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .background(LightGrey)
    ) {
        Row {

            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(15.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )

        }

    }
}


@Composable
fun WeatherState() {
    Row() {
        Column() {
            Text(
                text = "", textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp
            )
            Text(
                text = "Scattered Clouds", textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif, color = Color.Black, fontSize = 12.sp
            )
        }
        Image(
            painter = painterResource(id = com.lubnamariyam.soho.R.drawable.rainy),
            contentDescription = "sun",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Preview(name = "profile")
@Composable
private fun FeaturedCoursePreview() {
    SohoTheme {
    }

}

