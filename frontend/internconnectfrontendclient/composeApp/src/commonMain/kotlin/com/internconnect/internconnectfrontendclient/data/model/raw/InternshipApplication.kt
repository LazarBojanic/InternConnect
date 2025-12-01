package com.internconnect.internconnectfrontendclient.data.model.raw

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.data.model.joined.StudentJoined
import kotlinx.serialization.Serializable

@Entity(tableName = "internship_application")
data class InternshipApplication(
	@PrimaryKey(autoGenerate = false)
	val id: String,
	@ColumnInfo(name = "internship_id")
	val internshipId: String,
	@ColumnInfo(name = "student_id")
	val studentId: String,
	@ColumnInfo(name = "status")
	val status: InternshipApplicationStatus,
	@ColumnInfo(name = "resume")
	val resume: String,
	@ColumnInfo(name = "interview_notes")
	val interviewNotes: String?,
	@ColumnInfo(name = "created_at")
	val createdAt: String,
	@ColumnInfo(name = "updated_at")
	val updatedAt: String
)
enum class InternshipApplicationStatus {
	APPLIED, REJECTED, ACCEPTED
}