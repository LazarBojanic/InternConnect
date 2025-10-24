package com.lazar.internconnectfrontendclient.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lazar.internconnectfrontendclient.theme.AppTheme

@Composable
fun PrimaryButton(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
) {
	Button(
		onClick = onClick,
		enabled = enabled,
		modifier = Modifier
			.fillMaxWidth()
			.defaultMinSize(minHeight = 48.dp)
			.padding(horizontal = 16.dp)
			.then(modifier),
		colors = ButtonDefaults.buttonColors(
			containerColor = MaterialTheme.colorScheme.primary,
			contentColor = MaterialTheme.colorScheme.onPrimary,
			disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
			disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f)
		)
	) {
		Text(text)
	}
}
@Composable
fun SecondaryButton(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
) {
	OutlinedButton(
		onClick = onClick,
		enabled = enabled,
		border = BorderStroke(2.dp, Color.White),
		colors = ButtonDefaults.outlinedButtonColors(
			containerColor = Color.Transparent,
			contentColor = Color.White
		),
		modifier = Modifier
			.fillMaxWidth()
			.defaultMinSize(minHeight = 48.dp)
			.padding(horizontal = 16.dp)
			.then(modifier),
	) {
		Text(text)
	}
}


@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
) {
    val base = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label?.let { { Text(it) } },
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = base.then(modifier),
    )
}
