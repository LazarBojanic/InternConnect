package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.ui.components.RegisterCompanyMemberPane
import com.internconnect.internconnectfrontendclient.ui.components.RegisterStudentPane

@Composable
fun RegisterScreen(
	onSuccess: () -> Unit,
	onNavigateBack: () -> Unit,
) {
	var tabIndex by remember { mutableStateOf(0) }
	val tabs = listOf("Student", "Company Member")

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Text(text = "Register", style = MaterialTheme.typography.headlineSmall)
		Spacer(Modifier.height(8.dp))

		PrimaryTabRow(selectedTabIndex = tabIndex) {
			tabs.forEachIndexed { index, title ->
				Tab(
					selected = tabIndex == index,
					onClick = { tabIndex = index },
					text = { Text(title) }
				)
			}
		}
		Spacer(Modifier.height(16.dp))

		when (tabIndex) {
			0 -> RegisterStudentPane(onSuccess, onNavigateBack)
			else -> RegisterCompanyMemberPane(onSuccess, onNavigateBack)
		}
	}
}
