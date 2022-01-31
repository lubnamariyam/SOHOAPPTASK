package com.lubnamariyam.soho.ui.theme

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lubnamariyam.soho.R
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController ){
    val scale = remember {
        Animatable(0f)
    }

    //println("Hello-->" + homeViewModel.getProductList())

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )

        delay(2000L)
        navController.navigate("home_screen")
    }

    // Image -> Logo
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .size(300.dp, 300.dp)
        )
    }
}