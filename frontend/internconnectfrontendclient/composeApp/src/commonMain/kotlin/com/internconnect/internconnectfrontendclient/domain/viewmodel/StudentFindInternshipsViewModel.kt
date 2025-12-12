package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.data.toJoined
import com.internconnect.internconnectfrontendclient.domain.util.DummyData
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentFindInternshipsViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val internships: List<InternshipJoined> = emptyList(),
		val categories: List<String> = emptyList(),
		val error: String? = null,
		val useDummy: Boolean = true,
	)

	private val scope = CoroutineScope(Dispatchers.IO)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) {
		if (_state.value.useDummy == use) return
		_state.value = _state.value.copy(useDummy = use)
		load()
	}

	fun load() {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				if (_state.value.useDummy) {
					_state.value = _state.value.copy(
						loading = false,
						internships = DummyData.internships(),
						categories = DummyData.categories()
					)
				} else {
					val internshipsDto = api.fetchInternships().orEmpty()
					val categories = api.fetchCategories().orEmpty()
					val internships = internshipsDto.map { it.toJoined() }
					_state.value = _state.value.copy(
						loading = false,
						internships = internships,
						categories = categories
					)
				}
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load internships")
			}
		}
	}
}