package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberPostInternshipViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import org.koin.compose.koinInject

@Composable
fun CompanyMemberPostInternshipScreen(onBack: () -> Unit) {
	val vm: CompanyMemberPostInternshipViewModel = koinInject()
	val state by vm.state.collectAsState()

	var title by remember { mutableStateOf("") }
	var category by remember { mutableStateOf("") }
	var description by remember { mutableStateOf("") }
	var useDummy by remember { mutableStateOf(true) }

	LaunchedEffect(useDummy) { vm.setUseDummy(useDummy) }

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Post Internship", onBack = onBack)
		Spacer(Modifier.height(16.dp))
		/*Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
			Text("Dummy data")
			Switch(checked = useDummy, onCheckedChange = { useDummy = it })
		}*/
		Spacer(Modifier.height(12.dp))

		OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
		Spacer(Modifier.height(8.dp))
		OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())
		Spacer(Modifier.height(8.dp))
		OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())

		Spacer(Modifier.height(16.dp))
		Button(onClick = { vm.postInternship(title, category, description) }, enabled = !state.loading) { Text("Post") }

		Spacer(Modifier.height(12.dp))
		if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
		state.successMessage?.let { Text(it, color = MaterialTheme.colorScheme.primary) }
		state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
	}
}