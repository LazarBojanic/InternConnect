package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.LoginUserViewModel
import com.internconnect.internconnectfrontendclient.domain.RegisterCompanyViewModel
import com.internconnect.internconnectfrontendclient.domain.RegisterStudentViewModel
import com.internconnect.internconnectfrontendclient.dto.LoginUserDto
import com.internconnect.internconnectfrontendclient.dto.RegisterCompanyDto
import com.internconnect.internconnectfrontendclient.dto.RegisterStudentDto
import com.internconnect.internconnectfrontendclient.ui.components.AppTextField
import com.internconnect.internconnectfrontendclient.ui.components.PasswordTextField
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
	onSuccess: () -> Unit,
	onNavigateBack: () -> Unit,
	onNavigateRegister: () -> Unit
) {
	val vm: LoginUserViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	fun submit() {
		vm.login(
			LoginUserDto(
				email = email.trim(),
				password = password,
				userAgent = "ComposeApp",
				ip = "0.0.0.0"
			)
		)
	}

	LaunchedEffect(state) {
		if (state is LoginUserViewModel.UIState.LoggedIn) {
			onSuccess()
		}
	}

	Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Column(
			modifier = Modifier.padding(24.dp).fillMaxWidth(),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			Text(text = "Login", style = MaterialTheme.typography.headlineSmall)
			AppTextField(
				value = email,
				onValueChange = { email = it },
				label = "Email",
				singleLine = true,
				modifier = Modifier.fillMaxWidth()
			)
			PasswordTextField(password, {password = it}, label = "Password", modifier = Modifier.fillMaxWidth())
			val loading = state is LoginUserViewModel.UIState.Loading
			Button(
				onClick = { submit() },
				enabled = !loading,
				modifier = Modifier.fillMaxWidth()
			) { Text(if (loading) "Logging in…" else "Login") }

			if (state is LoginUserViewModel.UIState.Error) {
				val msg = (state as LoginUserViewModel.UIState.Error).message
				Text(text = msg, color = MaterialTheme.colorScheme.error)
			}

			Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
				TextButton(onClick = onNavigateBack) { Text("Back") }
				TextButton(onClick = onNavigateRegister) { Text("Register") }
			}
		}
	}
}