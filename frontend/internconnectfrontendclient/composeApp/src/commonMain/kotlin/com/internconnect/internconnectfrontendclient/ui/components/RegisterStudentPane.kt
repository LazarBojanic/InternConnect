package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.RegisterStudentViewModel
import com.internconnect.internconnectfrontendclient.dto.RegisterStudentDto
import org.koin.compose.koinInject


@Composable
fun RegisterStudentPane(onSuccess: () -> Unit, onBack: () -> Unit) {
	val vm: RegisterStudentViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var confirmPassword by remember { mutableStateOf("") }
	var firstName by remember { mutableStateOf("") }
	var lastName by remember { mutableStateOf("") }
	var schoolName by remember { mutableStateOf("") }
	var grade by remember { mutableStateOf(1) }

	fun submit() {
		vm.register(
			RegisterStudentDto(
				email = email.trim(),
				password = password.trim(),
				confirmPassword = confirmPassword.trim(),
				firstName = firstName.trim(),
				lastName = lastName.trim(),
				schoolName = schoolName.trim(),
				grade = grade
			)
		)
	}

	LaunchedEffect(state) {
		if (state is RegisterStudentViewModel.UIState.Registered) onSuccess()
	}

	Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
		AppTextField(email, { email = it }, label = "Email", modifier = Modifier.fillMaxWidth())
		PasswordTextField(password, {password = it}, label = "Password", modifier = Modifier.fillMaxWidth())
		PasswordTextField(confirmPassword, {confirmPassword = it}, label = "Confirm Password", modifier = Modifier.fillMaxWidth())
		AppTextField(firstName, { firstName = it }, label = "First Name", modifier = Modifier.fillMaxWidth())
		AppTextField(lastName, { lastName = it }, label = "Last Name", modifier = Modifier.fillMaxWidth())
		AppTextField(schoolName, { schoolName = it }, label = "School Name", modifier = Modifier.fillMaxWidth())
		Slider(value = grade.toFloat(), onValueChange = { grade = it.toInt() }, valueRange = 1f..12f, steps = 11, modifier = Modifier.fillMaxWidth())
		Spacer(Modifier.height(12.dp))

		val loading = state is RegisterStudentViewModel.UIState.Loading
		Button(onClick = { submit() }, enabled = !loading, modifier = Modifier.fillMaxWidth()) {
			Text(if (loading) "Registering…" else "Register as Student")
		}
		if (state is RegisterStudentViewModel.UIState.Error) {
			val msg = (state as RegisterStudentViewModel.UIState.Error).message
			Text(msg, color = MaterialTheme.colorScheme.error)
		}
		TextButton(onClick = onBack) { Text("Back") }
	}
}

