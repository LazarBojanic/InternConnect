package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TwoColumnGrid(labels: List<Pair<String, () -> Unit>>) {
	Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
		labels.chunked(2).forEach { rowItems ->
			Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
				rowItems.forEach { (label, action) ->
					BoxButton(label = label, onClick = action, modifier = Modifier.weight(1f))
				}
				if (rowItems.size == 1) {
					Spacer(modifier = Modifier.weight(1f))
				}
			}
		}
	}
}