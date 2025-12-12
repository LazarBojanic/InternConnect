package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.RegisterCompanyMemberViewModel
import com.internconnect.internconnectfrontendclient.data.model.dto.request.RegisterCompanyMemberDto
import org.koin.compose.koinInject

@Composable
fun RegisterCompanyMemberPane(onSuccess: () -> Unit, onBack: () -> Unit) {
	val vm: RegisterCompanyMemberViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	var userEmail by remember { mutableStateOf("") }
	var userFirstName by remember { mutableStateOf("") }
	var userLastName by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }
	var companyName by remember { mutableStateOf("") }
	var companyIndustry by remember { mutableStateOf("") }

	fun submit() {
		vm.register(
			RegisterCompanyMemberDto(
				userEmail = userEmail.trim(),
				userFirstName = userFirstName.trim(),
				userLastName = userLastName.trim(),
				password = password.trim(),
				confirmPassword = confirmPassword.trim(),
				companyName = companyName.trim(),
				companyIndustry = companyIndustry.trim()
			)
		)
	}

	LaunchedEffect(state) {
		if (state is RegisterCompanyMemberViewModel.UiState.Success) onSuccess()
	}

	Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
		AppTextField(userEmail, { userEmail = it }, label = "User Email", modifier = Modifier.fillMaxWidth())
		PasswordTextField(password, {password = it}, label = "Password", modifier = Modifier.fillMaxWidth())
		PasswordTextField(confirmPassword, {confirmPassword = it}, label = "Confirm Password", modifier = Modifier.fillMaxWidth())
		AppTextField(userFirstName, { userFirstName = it }, label = "User First Name", modifier = Modifier.fillMaxWidth())
		AppTextField(userLastName, { userLastName = it }, label = "User Last Name", modifier = Modifier.fillMaxWidth())
		AppTextField(companyName, { companyName = it }, label = "Company Name", modifier = Modifier.fillMaxWidth())
		AppTextField(companyIndustry, { companyIndustry = it }, label = "Company Industry", modifier = Modifier.fillMaxWidth())
		Spacer(Modifier.height(12.dp))


		val loading = state is RegisterCompanyMemberViewModel.UiState.Loading
		Button(onClick = { submit() }, enabled = !loading, modifier = Modifier.fillMaxWidth()) {
			Text(if (loading) "Registering…" else "Register as Company Member")
		}
		if (state is RegisterCompanyMemberViewModel.UiState.Error) {
			val msg = (state as RegisterCompanyMemberViewModel.UiState.Error).message
			Text(msg, color = MaterialTheme.colorScheme.error)
		}
		TextButton(onClick = onBack) { Text("Back") }
	}
}