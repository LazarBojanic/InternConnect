package com.internconnect.internconnectfrontendclient.ui.screen.student
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.ui.components.CategoryFilter
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.InternshipCard
@Composable
fun StudentFindInternshipsScreen(
	internships: List<InternshipJoined>,
	categories: List<String>,
	onBack: (() -> Unit)? = null,
	onOpenDetails: (id: String) -> Unit,
) {
	var query by remember { mutableStateOf("") }
	var selectedCategory by remember { mutableStateOf("All") }

	val display = internships.filter { item ->
		(selectedCategory == "All" || item.category == selectedCategory) &&
			(query.isBlank() || item.title.contains(query, ignoreCase = true) || item.company.name.contains(query, ignoreCase = true))
	}

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Find Internships", onBack = onBack)
		Spacer(Modifier.height(16.dp))

		OutlinedTextField(
			value = query,
			onValueChange = { query = it },
			label = { Text("Search by role or company") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(Modifier.height(12.dp))

		CategoryFilter(
			categories = listOf("All") + categories.distinct(),
			selected = selectedCategory,
			onSelect = { selectedCategory = it }
		)

		Spacer(Modifier.height(16.dp))

		LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {
			items(display) { item ->
				InternshipCard(
					item,
					onApply = { onOpenDetails(item.id) },
					modifier = Modifier.fillMaxWidth()
				)
			}
		}
	}
}