package com.example.garden.screens.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.garden.R
import com.example.garden.screens.widgets.text.ChapterText
import com.example.garden.screens.widgets.text.ContentText

@Preview
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 65.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ChapterText(text = stringResource(R.string.authors))
        Spacer(modifier = Modifier.height(10.dp))
        ContentText(text = stringResource(R.string.vinogradova_margarita))
        ContentText(text = stringResource(R.string.horoshkova_alexandra))
        ContentText(text = stringResource(R.string.starodubov_maxim))
    }
}
