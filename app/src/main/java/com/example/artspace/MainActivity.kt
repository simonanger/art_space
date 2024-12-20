package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtworkWall(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
fun ArtworkWall(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.main_image
        2 -> R.drawable.metro
        3 -> R.drawable.tlon
        else -> R.drawable.main_image
    }
    val image = painterResource(imageResource)
    Box(modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(9.dp)
                    .fillMaxWidth()
            )
            ArtworkDescriptor(
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(onClick = {result -= 1}) {
                    Text(stringResource(R.string.previous))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Button(onClick = { result += 1 }) {
                    Text(stringResource(R.string.next))
                }
            }
        }
    }
}

@Composable
fun ArtworkDescriptor(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.title),
            fontSize = 32.sp,
            modifier = Modifier
                .padding(6.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.photographer),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(6.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun Buttons(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }
    Row(modifier = modifier) {
        Button(onClick = {if(result > 1){result -1}}) {
            Text(stringResource(R.string.previous))
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Button(onClick = { result +1 }) {
            Text(stringResource(R.string.next))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting("Android")
    }
}