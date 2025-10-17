package com.lazar.internconnectfrontendclient

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lazar.internconnectfrontendclient.ui.AppTheme
import internconnectfrontendclient.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun App() {
    AppTheme {
        Article(Modifier.fillMaxSize())
    }
}

@Composable
fun Article(modifier: Modifier){
    Column(modifier = modifier) {
        val articleImage = painterResource(Res.drawable.bg_compose_background)
        Image(painter = articleImage, contentDescription = "")
        val articleTitle = stringResource(Res.string.article_title)
        val articleDescription = stringResource(Res.string.article_description)
        val articleContent = stringResource(Res.string.article_content)
        Text(text = articleTitle, modifier = Modifier.padding(16.dp), fontSize = 24.sp)
        Text(text = articleDescription, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Justify)
        Text(text = articleContent, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Justify)
    }
}