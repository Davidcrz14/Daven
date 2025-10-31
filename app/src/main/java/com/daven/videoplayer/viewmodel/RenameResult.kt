package com.daven.videoplayer.viewmodel

import android.content.IntentSender
import com.daven.videoplayer.model.Video

sealed class RenameResult {
    data class Success(val updatedVideo: Video) : RenameResult()
    data class RequiresPermission(val intentSender: IntentSender) : RenameResult()
    data class Failure(val error: Throwable? = null) : RenameResult()
    object InvalidName : RenameResult()
}
