package com.heesoo.core.extensions

import com.heesoo.core.helper.StateHelper


suspend fun <T> StateHelper.actionWithLoading(action: suspend () -> T): T {
    return try {
        updateIsLoading(isVisible = true)
        action()
    } finally {
        updateIsLoading(isVisible = false)
    }
}