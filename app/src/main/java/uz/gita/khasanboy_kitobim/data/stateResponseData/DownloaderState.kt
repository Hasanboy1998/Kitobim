package uz.gita.khasanboy_kitobim.data.stateResponseData

sealed interface DownloaderState {
    object NotFoundFileId: DownloaderState
    class Downloading(val progress: Long): DownloaderState
    object NotDownloading: DownloaderState
    object Downloaded: DownloaderState
    object FailedDownloading: DownloaderState
}