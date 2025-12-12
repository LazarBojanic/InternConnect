package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplicationStatus
import com.internconnect.internconnectfrontendclient.data.toJoined
import com.internconnect.internconnectfrontendclient.domain.util.DummyData
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyMemberApplicationDetailsViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val application: InternshipApplicationJoined? = null,
		val error: String? = null,
		val useDummy: Boolean = true,
	)

	private val scope = CoroutineScope(Dispatchers.IO)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { if (_state.value.useDummy != use) _state.value = _state.value.copy(useDummy = use) }

	fun load(internshipId: String, applicationId: String) {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				val app = if (_state.value.useDummy) {
					DummyData.internshipApplications().firstOrNull { it.id == applicationId }
				} else {
					api.fetchCandidates(internshipId).orEmpty().map { it.toJoined() }.firstOrNull { it.id == applicationId }
				}
				_state.value = _state.value.copy(loading = false, application = app)
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load application")
			}
		}
	}

	fun updateStatus(applicationId: String, status: String) {
		scope.launch {
			try {
				if (_state.value.useDummy) {
					val current = _state.value.application ?: return@launch
					_state.value = _state.value.copy(application = current.copy(status = enumValueOf<InternshipApplicationStatus>(status)))
				} else {
					api.updateApplicationStatus(applicationId, status)
					val current = _state.value.application ?: return@launch
					_state.value = _state.value.copy(application = current.copy(status = enumValueOf<InternshipApplicationStatus>(status)))
				}
			} catch (_: Throwable) { }
		}
	}
}