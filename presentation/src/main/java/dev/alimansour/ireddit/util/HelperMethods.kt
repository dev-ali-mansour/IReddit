package dev.alimansour.ireddit.util

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import dev.alimansour.domain.model.Post
import dev.alimansour.ireddit.R

fun Activity.hideSoftKeyboard() {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.navigateToPost(post: Post) {
    val builder = CustomTabsIntent.Builder()
    val defaultColors = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(ContextCompat.getColor(this, R.color.purple_500))
        .build()
    builder.setDefaultColorSchemeParams(defaultColors)
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(this, Uri.parse(post.url))
}
