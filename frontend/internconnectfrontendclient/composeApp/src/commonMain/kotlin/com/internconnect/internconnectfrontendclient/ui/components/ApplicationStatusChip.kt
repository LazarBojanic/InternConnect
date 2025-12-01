package com.internconnect.internconnectfrontendclient.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationStatus

@Composable
fun ApplicationStatusChip(status: InternshipApplicationStatus) {
	val bg = when (status) {
		InternshipApplicationStatus.APPLIED -> MaterialTheme.colorScheme.secondaryContainer
		InternshipApplicationStatus.REJECTED -> MaterialTheme.colorScheme.errorContainer
		InternshipApplicationStatus.ACCEPTED -> MaterialTheme.colorScheme.tertiaryContainer
	}
	Surface(shape = MaterialTheme.shapes.small, color = bg, tonalElevation = 0.dp) {
		Text(text = status.name, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp), style = MaterialTheme.typography.labelMedium)
	}
}