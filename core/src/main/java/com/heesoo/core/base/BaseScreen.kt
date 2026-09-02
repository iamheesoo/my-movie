package com.heesoo.yeogi_problem.presentation.base

import androidx.compose.runtime.Composable

abstract class BaseScreen {
    @Composable
    abstract fun Create()

    @Composable
    abstract fun Effect()
}