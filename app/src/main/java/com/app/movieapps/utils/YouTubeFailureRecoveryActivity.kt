package com.app.movieapps.utils

import android.content.Intent
import android.widget.Toast
import com.app.movieapps.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

abstract class YouTubeFailureRecoveryActivity:YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener {
    private val tagRecoveryDialogRequest = 1

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult
    ) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, tagRecoveryDialogRequest).show()
        } else {
            val errorMessage = String.format(getString(R.string.error_player), errorReason.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == tagRecoveryDialogRequest) {
            getYouTubePlayerProvider().initialize(getString(R.string.youtube_key), this)
        }
    }

    protected abstract fun getYouTubePlayerProvider(): YouTubePlayer.Provider
}