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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
        1 -> R.drawable.isla_01
        2 -> R.drawable.isla_02
        3 -> R.drawable.isla_03
        4 -> R.drawable.isla_04
        5 -> R.drawable.isla_05
        6 -> R.drawable.isla_06
        7 -> R.drawable.isla_07
        8 -> R.drawable.isla_08
        9 -> R.drawable.isla_09
        10 -> R.drawable.isla_10
        else -> R.drawable.error
    }

    val image = painterResource(imageResource)
    Box(modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = result.toString(),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(9.dp)
                    .fillMaxWidth(),
                alignment = Alignment.Center
            )
           /* ArtworkDescriptor(
                modifier = Modifier
                    .fillMaxWidth()
            )*/
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {if(result > 1) {result -= 1} else result = 10},
                    modifier = Modifier.widthIn(150.dp)
                ) {
                    Text(stringResource(R.string.previous))
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Button(
                    onClick = {if(result < 10) {result += 1} else result = 1 },
                    modifier = Modifier.widthIn(150.dp)
                ) {
                    Text(stringResource(R.string.next))
                }
            }
        }
    }
}

fun nextImage(imageId: Int): Int {
    var result = 1
    if(imageId == 10) {
        return result
    } else result += 1
    return result

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