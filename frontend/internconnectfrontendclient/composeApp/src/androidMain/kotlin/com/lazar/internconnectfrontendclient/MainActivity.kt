package com.lazar.internconnectfrontendclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lazar.internconnectfrontendclient.android.ui.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Article(Modifier.fillMaxSize())
            }
        }

    }
    @Composable
    fun Article(modifier: Modifier){
        Column(modifier = modifier) {
            val articleImage = painterResource(R.drawable.bg_compose_background)
            Image(painter = articleImage, contentDescription = "")
            val articleTitle = stringResource(R.string.articleTitle)
            val articleDescription = stringResource(R.string.articleDescription)
            val articleContent = stringResource(R.string.articleContent)
            Text(text = articleTitle, modifier = Modifier.padding(16.dp), fontSize = 24.sp)
            Text(text = articleDescription, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Justify)
            Text(text = articleContent, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Justify)
        }
    }

}
