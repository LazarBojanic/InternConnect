package com.internconnect.internconnectbackendktor.service.specification

import com.internconnect.internconnectbackendktor.model.joined.OAuthAccountJoined
import com.internconnect.internconnectbackendktor.model.raw.oauthaccount.OAuthAccount
import java.util.*

interface IOAuthAccountService {
	suspend fun getAll(): List<OAuthAccountJoined>
	suspend fun getById(id: UUID): OAuthAccountJoined?
	suspend fun create(oAuthAccount: OAuthAccount): OAuthAccountJoined?
	suspend fun update(oAuthAccount: OAuthAccount): OAuthAccountJoined?
	suspend fun delete(id: UUID): Boolean
}