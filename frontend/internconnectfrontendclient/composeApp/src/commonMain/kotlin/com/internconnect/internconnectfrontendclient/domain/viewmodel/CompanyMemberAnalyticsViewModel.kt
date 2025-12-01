package com.internconnect.internconnectfrontendclient.domain.viewmodel

import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.data.toJoined
import com.internconnect.internconnectfrontendclient.domain.util.DummyData
import com.internconnect.internconnectfrontendclient.http.IAppApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompanyAnalyticsViewModel(private val api: IAppApi) {
	data class UiState(
		val loading: Boolean = false,
		val totalPostings: Int = 0,
		val totalApplicants: Int = 0,
		val byStatus: Map<String, Int> = emptyMap(),
		val latestPostings: List<InternshipJoined> = emptyList(),
		val error: String? = null,
		val useDummy: Boolean = true,
	)

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { if (_state.value.useDummy != use) { _state.value = _state.value.copy(useDummy = use); load() } }

	fun load() {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				val (postings, applications) = if (_state.value.useDummy) {
					val companyId = "comp-001"
					val postings = DummyData.internships().filter { it.company.id == companyId }
					val apps = DummyData.internshipApplications().filter { app -> postings.any { it.id == app.internship.id } }
					postings to apps
				} else {
					val postings = api.fetchCompanyInternships().orEmpty().map { it.toJoined() }
					// Aggregate all candidates for each posting
					val apps = postings.flatMap { posting ->
						api.fetchCandidates(posting.id).orEmpty().map { it.toJoined() }
					}
					postings to apps
				}
				val by = applications.groupBy { it.status.name }.mapValues { it.value.size }
				_state.value = _state.value.copy(
					loading = false,
					totalPostings = postings.size,
					totalApplicants = applications.size,
					byStatus = by,
					latestPostings = postings.take(5)
				)
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load analytics")
			}
		}
	}
}