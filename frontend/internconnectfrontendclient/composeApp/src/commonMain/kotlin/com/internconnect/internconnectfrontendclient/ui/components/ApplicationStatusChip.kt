package com.internconnect.internconnectfrontendclient.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.dto.ApplicationStatus
import com.internconnect.internconnectfrontendclient.ui.components.Header

@Composable
fun ApplicationStatusChip(status: ApplicationStatus) {
	val bg = when (status) {
		ApplicationStatus.APPLIED -> MaterialTheme.colorScheme.secondaryContainer
		ApplicationStatus.REJECTED -> MaterialTheme.colorScheme.errorContainer
		ApplicationStatus.ACCEPTED -> MaterialTheme.colorScheme.tertiaryContainer
	}
	Surface(shape = MaterialTheme.shapes.small, color = bg, tonalElevation = 0.dp) {
		Text(text = status.name, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp), style = MaterialTheme.typography.labelMedium)
	}
}