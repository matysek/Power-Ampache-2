package luci.sixsixsix.powerampache2.presentation.song_detail

import luci.sixsixsix.powerampache2.domain.models.Song

data class SongDetailState (
    val song: Song,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val isFetchingMore: Boolean = false
)
