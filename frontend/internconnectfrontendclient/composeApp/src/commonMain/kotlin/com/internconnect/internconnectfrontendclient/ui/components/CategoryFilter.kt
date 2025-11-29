package com.internconnect.internconnectfrontendclient.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun CategoryFilter(
	categories: List<String>,
	selected: String,
	onSelect: (String) -> Unit
) {
	var expanded by remember { mutableStateOf(false) }

	Box(modifier = Modifier.fillMaxWidth()) {
		OutlinedButton(
			onClick = { expanded = true },
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = selected)
			Icon(
				imageVector = Icons.Filled.ArrowDropDown,
				contentDescription = null
			)
		}

		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false }
		) {
			categories.forEach { option ->
				DropdownMenuItem(
					text = { Text(option) },
					onClick = {
						onSelect(option)
						expanded = false
					}
				)
			}
		}
	}
}