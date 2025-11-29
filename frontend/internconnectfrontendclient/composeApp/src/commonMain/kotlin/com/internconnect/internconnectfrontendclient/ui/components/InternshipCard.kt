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
import com.internconnect.internconnectfrontendclient.data.dto.InternshipDto

@Composable
fun InternshipCard(
	internshipDto: InternshipDto,
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
			Text(internshipDto.position, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
			Text(internshipDto.company, style = MaterialTheme.typography.bodyMedium)
			Text(internshipDto.country + " " + internshipDto.city, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
			Text(internshipDto.description, style = MaterialTheme.typography.bodyMedium)
			Spacer(Modifier.height(8.dp))
			Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
				Button(onClick = onApply, shape = RoundedCornerShape(12.dp)) { Text("Apply") }
			}
		}
	}
}