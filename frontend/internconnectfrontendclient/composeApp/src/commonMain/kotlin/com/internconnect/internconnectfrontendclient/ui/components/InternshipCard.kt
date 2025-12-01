package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined

@Composable
fun InternshipCard(
	internship: InternshipJoined,
	onApply: () -> Unit,
	modifier: Modifier = Modifier
) {
	Surface(
		modifier = modifier,
		shape = RoundedCornerShape(16.dp),
		tonalElevation = 2.dp,
		shadowElevation = 2.dp
	) {
		Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
			Text(internship.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
			Text(internship.company.name, style = MaterialTheme.typography.bodyMedium)
			Text(internship.company.country + " " + internship.company.city, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
			Text(internship.description, style = MaterialTheme.typography.bodyMedium)
			Spacer(Modifier.height(8.dp))
			Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
				Button(onClick = onApply, shape = RoundedCornerShape(12.dp)) { Text("Apply") }
			}
		}
	}
}