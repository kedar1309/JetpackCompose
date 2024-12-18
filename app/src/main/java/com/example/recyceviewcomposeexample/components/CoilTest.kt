package com.example.recyceviewcomposeexample.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.recyceviewcomposeexample.R

@Composable
fun CoilTest() {

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp), contentAlignment = Alignment.Center
    ) {
        val painter = rememberImagePainter(data = "https://i.imgur.com/CzXTtJV.jpg", builder = {
            placeholder(R.drawable.ic_launcher_background)
            crossfade(enable = true)
            error(R.drawable.error)
            transformations(
                //CircleCropTransformation(),
                BlurTransformation(LocalContext.current),
                RoundedCornersTransformation()
            )
        })

        Image(painter = painter, contentDescription = "Cat image")
        val state = painter.state
        if (state is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

    }

}