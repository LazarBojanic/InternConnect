package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.data.toJoined
import com.internconnect.internconnectfrontendclient.domain.util.DummyData
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyMemberDashboardViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val internships: List<InternshipJoined> = emptyList(),
		val error: String? = null,
		val useDummy: Boolean = true,
		// stats
		val postedCount: Int = 0,
		val activeApplications: Int = 0,
		val pendingReviews: Int = 0,
	)

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { if (_state.value.useDummy != use) { _state.value = _state.value.copy(useDummy = use); load() } }

	fun load() {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				val postings: List<InternshipJoined>
				val active: Int
				val pending: Int
				if (_state.value.useDummy) {
					val companyId = "comp-001"
					postings = DummyData.internships().filter { it.company.id == companyId }
					val apps = DummyData.internshipApplications().filter { app -> postings.any { it.id == app.internship.id } }
					active = apps.size
					pending = apps.count { it.status.name == "APPLIED" }
				} else {
					postings = api.fetchCompanyInternships().orEmpty().map { it.toJoined() }
					val allApps = postings.flatMap { p -> api.fetchCandidates(p.id).orEmpty() }.map { it.toJoined() }
					active = allApps.size
					pending = allApps.count { it.status.name == "APPLIED" }
				}
				_state.value = _state.value.copy(
					loading = false,
					internships = postings,
					postedCount = postings.size,
					activeApplications = active,
					pendingReviews = pending,
				)
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load internships")
			}
		}
	}
}