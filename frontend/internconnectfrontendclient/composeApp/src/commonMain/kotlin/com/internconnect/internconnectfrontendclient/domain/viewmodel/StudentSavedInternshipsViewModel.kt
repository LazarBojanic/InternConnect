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

class StudentSavedInternshipsViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val saved: List<InternshipJoined> = emptyList(),
		val error: String? = null,
		val useDummy: Boolean = true,
	)

	private val scope = CoroutineScope(Dispatchers.IO)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { if (_state.value.useDummy != use) { _state.value = _state.value.copy(useDummy = use); load() } }

	fun load() {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				if (_state.value.useDummy) {
					_state.value = _state.value.copy(loading = false, saved = DummyData.savedInternships())
				} else {
					val list = api.fetchSavedInternships().orEmpty().map { it.toJoined() }
					_state.value = _state.value.copy(loading = false, saved = list)
				}
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load saved internships")
			}
		}
	}
}