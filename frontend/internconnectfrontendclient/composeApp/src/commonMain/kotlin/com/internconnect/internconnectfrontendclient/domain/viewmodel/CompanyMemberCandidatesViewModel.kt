package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.data.model.raw.InternshipApplicationStatus
import com.internconnect.internconnectfrontendclient.data.toJoined
import com.internconnect.internconnectfrontendclient.domain.util.DummyData
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyCandidatesViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val applications: List<InternshipApplicationJoined> = emptyList(),
		val error: String? = null,
		val useDummy: Boolean = true,
	)

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { if (_state.value.useDummy != use) { _state.value = _state.value.copy(useDummy = use) } }

	fun load(internshipId: String) {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				if (_state.value.useDummy) {
					// Filter dummy applications by given internshipId
					val apps = DummyData.internshipApplications().filter { it.internship.id == internshipId }
					_state.value = _state.value.copy(loading = false, applications = apps)
				} else {
					val apps = api.fetchCandidates(internshipId).orEmpty().map { it.toJoined() }
					_state.value = _state.value.copy(loading = false, applications = apps)
				}
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load candidates")
			}
		}
	}

	fun updateStatus(applicationId: String, status: String) {
		scope.launch {
			try {
				if (_state.value.useDummy) {
					// no-op in dummy; optimistically update UI
					_state.value = _state.value.copy(applications = _state.value.applications.map {
						if (it.id == applicationId) {
							it.copy(status = enumValueOf<InternshipApplicationStatus>(status))
						}
						else{
							it
						}
					})
				} else {
					api.updateApplicationStatus(applicationId, status)
					// refetch or optimistically update; here, optimistic:
					_state.value = _state.value.copy(applications = _state.value.applications.map {
						if (it.id == applicationId) {
							it.copy(status = enumValueOf<InternshipApplicationStatus>(status))
						}
						else{
							it
						}					})
				}
			} catch (_: Throwable) { /* ignore for now */ }
		}
	}
}