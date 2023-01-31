package com.example.affirmations
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme
import com.example.affirmations.ui.theme.Blue100
import com.example.affirmations.ui.theme.Blue300
import com.example.affirmations.ui.theme.Blue700

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationApp()
        }
    }
}

@Composable
fun AffirmationApp() {
    AffirmationsTheme {
        Column (
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = colorResource(id = R.color.blue_200))
                    .fillMaxWidth()
                    .padding(
                        vertical = 24.dp
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = colorResource(id = R.color.blue_900),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            }

            AffirmationList(affirmationList = Datasource().loadAffirmations())
        }
    }
}

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
        backgroundColor = colorResource(id = R.color.gray_700)
    ) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6,
                color = colorResource(id = R.color.blue_200)
            )
        }
    }
}

@Composable
private fun AffirmationList(
    affirmationList: List<Affirmation>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        Modifier.background(color = Blue100)
        items(affirmationList) {
            affirmation -> AffirmationCard(affirmation = affirmation)
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(
        affirmation = Affirmation(
            R.string.affirmation1,
            imageResourceId = R.drawable.image1
        )
    )
}