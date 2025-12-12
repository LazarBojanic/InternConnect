package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.dto.response.CompanyDto
import com.internconnect.internconnectfrontendclient.data.model.dto.response.InternshipDto
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyMemberPostInternshipViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val successMessage: String? = null,
		val error: String? = null,
		val useDummy: Boolean = true,
	)

	private val scope = CoroutineScope(Dispatchers.IO)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { _state.value = _state.value.copy(useDummy = use) }

	fun postInternship(title: String, category: String, description: String) {
		scope.launch {
			_state.value = _state.value.copy(loading = true, successMessage = null, error = null)
			try {
				if (_state.value.useDummy) {
					// pretend success
					_state.value = _state.value.copy(loading = false, successMessage = "Internship posted (dummy)")
				} else {
					val dto = InternshipDto(
						id = "", // server generates
						company = CompanyDto(
							"",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
							""
						),
						title = title,
						category = category,
						description = description,
						createdAt = "",
						updatedAt = ""
					)
					val result = api.postInternship(dto)
					if (result != null) _state.value = _state.value.copy(loading = false, successMessage = result)
					else _state.value = _state.value.copy(loading = false, error = "Failed to post internship")
				}
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to post internship")
			}
		}
	}
}