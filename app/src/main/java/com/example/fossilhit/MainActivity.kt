package com.example.fossilhit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fossilhit.ui.theme.CenturyGothic
import com.example.fossilhit.ui.theme.FossilHitTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FossilHitTheme {
                FossilHitApp()
            }
        }
    }
}

@Composable
fun FossilHitApp() {
    FossilImageAndPunch()
}

@Composable
fun FossilImageAndPunch(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {

    var hitsLeft by remember { mutableStateOf(0) }
    var hitCount by remember { mutableStateOf(0) }
    var winCount by remember { mutableStateOf(0) }
    var failCount by remember { mutableStateOf(0) }
    var isChipped by remember { mutableStateOf(false) }
    var hammerSelected by remember { mutableStateOf(true) }

    val rockImage = when(isChipped) {
        true -> R.drawable.fossilchipped
        else -> R.drawable.fossil
    }

    val hammerSelectionImage = when(hammerSelected) {
        true -> R.drawable.orangebox
        else -> R.drawable.whitebox
    }

    val chiselSelectionImage = when(hammerSelected) {
        false -> R.drawable.orangebox
        else -> R.drawable.whitebox
    }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.blurred_generic_menu),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$winCount Wins",
                            fontFamily = CenturyGothic,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            color = Color.White,
                            style = androidx.compose.ui.text.TextStyle(
                                shadow = Shadow(
                                    color = Color(0, 0, 0, 64), offset = Offset(8.0f, 10.0f)
                                )
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.greenunpolishedfossil),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$failCount Fails",
                            fontFamily = CenturyGothic,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            color = Color.White,
                            style = androidx.compose.ui.text.TextStyle(
                                shadow = Shadow(
                                    color = Color(0, 0, 0, 64), offset = Offset(8.0f, 10.0f)
                                )
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.redunpolishedfossil),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                        )
                    }
                }
//                TextButton(onClick = { /*TODO*/ }) {
//                    Image(
//                        painter = painterResource(id = R.drawable.leaderboard_button),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(64.dp)
//                    )
//                }
            }
            Spacer(modifier = Modifier.height(42.dp))
            Image(
                painter = painterResource(rockImage),
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
            )
            Spacer(modifier = Modifier.height(42.dp))
            TextButton(
                onClick = {
                    if (hammerSelected || (hitCount == hitsLeft)) {
                        if (hitCount == 0) {
                            hitCount++
                            hitsLeft = (1..30).random()
                        } else {
                            hitCount++
                            if (hitCount == hitsLeft) {
                                isChipped = true
                            } else if (hitCount > hitsLeft) {
                                if (hammerSelected) {
                                    failCount++
                                    hitCount = 0
                                    isChipped = false
                                } else {
                                    winCount++
                                    hitCount = 0
                                    isChipped = false
                                }
                            }
                        }
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.punchicon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                )
            }
            Row() {
                TextButton(onClick = { hammerSelected = true }) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painterResource(hammerSelectionImage),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Image(
                            painterResource(id = R.drawable.rockhammer),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                        )
                    }
                }
                TextButton(onClick = { hammerSelected = false }) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painterResource(chiselSelectionImage),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Image(
                            painterResource(id = R.drawable.rockchisel),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    winCount = 0
                    failCount = 0
                    hitCount = 0
                    hitsLeft = 0
                    isChipped = false
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.slider_button),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "RESET",
                        fontFamily = CenturyGothic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 42.sp,
                        color = Color.White,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color(0, 0, 0, 64), offset = Offset(8.0f, 10.0f)
                            )
                        )
                    )
                }
            }
        }
    }
}
