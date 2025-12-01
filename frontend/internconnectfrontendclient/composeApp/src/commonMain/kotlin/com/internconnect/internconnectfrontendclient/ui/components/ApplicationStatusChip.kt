package com.internconnect.internconnectfrontendclient.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplicationStatus

private val StrongRed = Color(0xFFC62828)
private val StrongYellow = Color(0xFFF9A825)
private val StrongGreen = Color(0xFF2E7D32)
private val NeutralGrey = Color(0xFF424242)

@Composable
fun statusContainerColor(status: InternshipApplicationStatus): Color = when (status) {
	InternshipApplicationStatus.APPLIED -> StrongRed.copy(alpha = 0.10f)
	InternshipApplicationStatus.ACCEPTED  -> StrongYellow.copy(alpha = 0.12f)
	InternshipApplicationStatus.REJECTED -> StrongGreen.copy(alpha = 0.10f)
	else                                 -> NeutralGrey.copy(alpha = 0.10f)
}

@Composable
fun statusTextColor(status: InternshipApplicationStatus): Color = when (status) {
	InternshipApplicationStatus.APPLIED -> StrongRed
	InternshipApplicationStatus.ACCEPTED  -> StrongYellow
	InternshipApplicationStatus.REJECTED -> StrongGreen
	else                                 -> NeutralGrey
}

@Composable
fun ApplicationStatusChip(status: InternshipApplicationStatus, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 6.dp)) {
	Row(
		modifier
			.background(statusContainerColor(status), shape = MaterialTheme.shapes.small)
			.padding(contentPadding)
	) {
		Text(
			text = status.name.replace('_', ' ').lowercase().replaceFirstChar { it.uppercase() },
			color = statusTextColor(status),
			style = MaterialTheme.typography.labelMedium,
			fontWeight = FontWeight.SemiBold
		)
	}
}
