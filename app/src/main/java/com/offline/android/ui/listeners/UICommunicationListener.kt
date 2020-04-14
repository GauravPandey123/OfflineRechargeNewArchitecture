package com.offline.android.ui.listeners

import com.offline.android.ui.extensions.UIMessage

interface UICommunicationListener {

    fun onUIMessageReceived(uiMessage: UIMessage)
}