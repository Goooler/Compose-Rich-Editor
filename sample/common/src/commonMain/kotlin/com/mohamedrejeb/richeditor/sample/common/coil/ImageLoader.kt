package com.mohamedrejeb.richeditor.sample.common.coil

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import coil3.util.DebugLogger

@OptIn(ExperimentalCoilApi::class)
@Composable
fun setSingletonImageLoaderFactory() {
    coil3.compose.setSingletonImageLoaderFactory {
        newImageLoader(
            context = it,
            debug = false,
        )
    }
}

private fun newImageLoader(
    context: PlatformContext,
    debug: Boolean = false,
): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            add(KtorNetworkFetcherFactory())
            add(SvgDecoder.Factory())
        }
        .memoryCache {
            MemoryCache.Builder()
                // Set the max size to 25% of the app's available memory.
                .maxSizePercent(context, percent = 0.25)
                .build()
        }
        // Show a short crossfade when loading images asynchronously.
        .crossfade(true)
        // Enable logging if this is a debug build.
        .apply {
            if (debug) {
                logger(DebugLogger())
            }
        }
        .build()
}
