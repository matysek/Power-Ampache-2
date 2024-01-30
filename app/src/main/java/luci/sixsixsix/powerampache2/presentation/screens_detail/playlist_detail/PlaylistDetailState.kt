/**
 * Copyright (C) 2024  Antonio Tari
 *
 * This file is a part of Power Ampache 2
 * Ampache Android client application
 * @author Antonio Tari
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package luci.sixsixsix.powerampache2.presentation.screens_detail.playlist_detail

import luci.sixsixsix.powerampache2.domain.models.Playlist
import luci.sixsixsix.powerampache2.domain.models.Song
import luci.sixsixsix.powerampache2.presentation.common.SongWrapper

data class PlaylistDetailState (
    val playlist: Playlist = Playlist("", ""),
    val songs: List<SongWrapper> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isPlaylistRemoveLoading: Boolean = false,
    val searchQuery: String = "",
    val isFetchingMore: Boolean = false,
    val isUserOwner: Boolean = false
) {
    fun getSongList(): List<Song> = songs.map { it.song }
}
