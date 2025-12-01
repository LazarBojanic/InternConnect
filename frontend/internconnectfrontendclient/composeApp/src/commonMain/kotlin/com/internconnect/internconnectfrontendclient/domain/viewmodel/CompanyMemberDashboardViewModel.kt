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
	)

	private val scope = CoroutineScope(Dispatchers.Default)
	private val _state = MutableStateFlow(UiState())
	val state: StateFlow<UiState> = _state

	fun setUseDummy(use: Boolean) { if (_state.value.useDummy != use) { _state.value = _state.value.copy(useDummy = use); load() } }

	fun load() {
		scope.launch {
			_state.value = _state.value.copy(loading = true, error = null)
			try {
				if (_state.value.useDummy) {
					// Use a subset that represents "my" company’s postings (companyA from DummyData)
					val companyId = "comp-001"
					val mine = DummyData.internships().filter { it.company.id == companyId }
					_state.value = _state.value.copy(loading = false, internships = mine)
				} else {
					val list = api.fetchCompanyInternships().orEmpty().map { it.toJoined() }
					_state.value = _state.value.copy(loading = false, internships = list)
				}
			} catch (t: Throwable) {
				_state.value = _state.value.copy(loading = false, error = t.message ?: "Failed to load internships")
			}
		}
	}
}