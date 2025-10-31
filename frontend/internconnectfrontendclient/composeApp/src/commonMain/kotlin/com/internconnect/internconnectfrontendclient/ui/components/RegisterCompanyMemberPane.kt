package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.RegisterCompanyMemberViewModel
import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyMemberDto
import org.koin.compose.koinInject

@Composable
fun RegisterCompanyPane(onSuccess: () -> Unit, onBack: () -> Unit) {
	val vm: RegisterCompanyMemberViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }
	var name by remember { mutableStateOf("") }
	var industry by remember { mutableStateOf("") }

	fun submit() {
		vm.register(
			RegisterCompanyMemberDto(
				email = email.trim(),
				password = password.trim(),
				confirmPassword = confirmPassword.trim(),
				name = name.trim(),
				industry = industry.trim(),
			)
		)
	}

	LaunchedEffect(state) {
		if (state is RegisterCompanyMemberViewModel.UiState.Success) onSuccess()
	}

	Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
		AppTextField(email, { email = it }, label = "Email", modifier = Modifier.fillMaxWidth())
		PasswordTextField(password, {password = it}, label = "Password", modifier = Modifier.fillMaxWidth())
		PasswordTextField(confirmPassword, {confirmPassword = it}, label = "Confirm Password", modifier = Modifier.fillMaxWidth())
		AppTextField(name, { name = it }, label = "Name", modifier = Modifier.fillMaxWidth())
		AppTextField(industry, { industry = it }, label = "Industry", modifier = Modifier.fillMaxWidth())
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