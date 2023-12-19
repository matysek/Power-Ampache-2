package luci.sixsixsix.powerampache2.presentation.playlists

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luci.sixsixsix.powerampache2.common.Resource
import luci.sixsixsix.powerampache2.domain.MusicRepository
import luci.sixsixsix.powerampache2.presentation.songs.SongsEvent
import javax.inject.Inject


@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val repository: MusicRepository
) : ViewModel() {
    var state by mutableStateOf(PlaylistsState())
    private var searchJob: Job? = null

    init {
        getArtist()
    }

    fun onEvent(event: SongsEvent) {
        when(event) {
            is SongsEvent.Refresh -> {
                getArtist(fetchRemote = true)
            }
            is SongsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1500L)
                    getArtist()
                }
            }
        }
    }

    private fun getArtist(
        query: String = state.searchQuery.lowercase(),
        fetchRemote: Boolean = true
    ) {
        viewModelScope.launch {
            repository
                .getPlaylists(fetchRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { playlists ->
                                Log.d("aaaa", "${playlists.size}")
                                state = state.copy(playlists = playlists)
                            }
                        }
                        is Resource.Error -> {
                            Log.d("aaaa", "ERROR PlaylistsViewModel ${result.exception}")
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}
