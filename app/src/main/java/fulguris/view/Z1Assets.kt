package fulguris.view

import android.content.Context
import android.net.Uri
import android.webkit.WebResourceResponse
import androidx.webkit.WebViewAssetLoader

/**
 * ZEN BROWSER: serves the bundled Z1 app from app/src/main/assets/z1/
 * Open it at [HOME].
 */
object Z1Assets {
    const val HOST = "appassets.androidplatform.net"
    const val HOME = "https://$HOST/assets/z1/index.html"

    private var loader: WebViewAssetLoader? = null

    fun intercept(context: Context, uri: Uri): WebResourceResponse? {
        if (uri.host != HOST) return null
        val l = loader ?: WebViewAssetLoader.Builder()
            .setDomain(HOST)
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context.applicationContext))
            .build()
            .also { loader = it }
        return l.shouldInterceptRequest(uri)
    }
}
