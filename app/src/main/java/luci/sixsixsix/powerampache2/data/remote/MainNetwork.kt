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
package luci.sixsixsix.powerampache2.data.remote

import luci.sixsixsix.powerampache2.BuildConfig
import luci.sixsixsix.powerampache2.common.Constants.NETWORK_REQUEST_LIMIT_DEBUG
import luci.sixsixsix.powerampache2.common.Constants.NETWORK_REQUEST_LIMIT_HOME
import luci.sixsixsix.powerampache2.common.Constants.NETWORK_REQUEST_LIMIT_SONGS_BY_GENRE
import luci.sixsixsix.powerampache2.data.remote.dto.AlbumDto
import luci.sixsixsix.powerampache2.data.remote.dto.AlbumsResponse
import luci.sixsixsix.powerampache2.data.remote.dto.ArtistDto
import luci.sixsixsix.powerampache2.data.remote.dto.ArtistsResponse
import luci.sixsixsix.powerampache2.data.remote.dto.AuthDto
import luci.sixsixsix.powerampache2.data.remote.dto.GenresResponse
import luci.sixsixsix.powerampache2.data.remote.dto.GoodbyeDto
import luci.sixsixsix.powerampache2.data.remote.dto.PlaylistDto
import luci.sixsixsix.powerampache2.data.remote.dto.PlaylistsResponse
import luci.sixsixsix.powerampache2.data.remote.dto.ShareDto
import luci.sixsixsix.powerampache2.data.remote.dto.SongsResponse
import luci.sixsixsix.powerampache2.data.remote.dto.SuccessResponse
import luci.sixsixsix.powerampache2.data.remote.dto.UserDto
import luci.sixsixsix.powerampache2.domain.models.PlaylistType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.lang.StringBuilder

/**
 * Main network interface which will fetch a new welcome title for us
 */
interface MainNetwork {
    @GET("json.server.php?action=handshake")
    suspend fun authorize(@Query("auth") apiKey: String = API_KEY): AuthDto

    @GET("json.server.php?action=handshake")
    suspend fun authorize(
        @Query("auth") authHash: String,
        @Query("user") user: String,
        @Query("timestamp") timestamp: Long
    ): AuthDto

    @GET("json.server.php?action=ping")
    suspend fun ping(@Query("auth") authKey: String = ""): AuthDto

    @GET("json.server.php?action=goodbye")
    suspend fun goodbye(@Query("auth") authKey: String = ""): GoodbyeDto

    /**
     * Register as a new user if allowed.
     * (Requires the username, password and email.)
     *
     * Input        Type    Optional
     * 'username'	string  NO
     * 'password'	string  NO
     * 'email'	    string  NO
     * 'fullname'	string	YES
     *
     */
    @GET("json.server.php?action=register")
    suspend fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("email") email: String,
        @Query("fullname") fullName: String? = null
    ): SuccessResponse

    @GET("json.server.php?action=user")
    suspend fun getUser(
        @Query("auth") authKey: String,
        @Query("username") username: String): UserDto

    @GET("json.server.php?action=search_songs")
    suspend fun getSongs(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = NETWORK_REQUEST_LIMIT_DEBUG,
        @Query("filter") filter: String = "",
        @Query("exact") exact: Int = 0,
        @Query("offset") offset: Int = 0,
    ): SongsResponse // TODO remove default values

    @GET("json.server.php?action=albums")
    suspend fun getAlbums(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int,
        @Query("filter") filter: String = "",
        @Query("exact") exact: Int = 0,
        @Query("offset") offset: Int = 0,
        @Query("include") include: String = "", // albums, songs (includes track list)
    ): AlbumsResponse // TODO remove default values

    @GET("json.server.php?action=artists")
    suspend fun getArtists(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = NETWORK_REQUEST_LIMIT_DEBUG,
        @Query("filter") filter: String = "",
        @Query("exact") exact: Int = 0,
        @Query("offset") offset: Int = 0,
        @Query("include") include: String = "", // albums, songs (includes track list)
    ): ArtistsResponse // TODO remove default values

    @GET("json.server.php?action=playlists")
    suspend fun getPlaylists(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = 0,
        @Query("filter") filter: String = "",
        @Query("exact") exact: Int = 0,
        @Query("offset") offset: Int = 0,
        @Query("hide_search") hideSearch: Int = 0, // 0, 1 (if true do not include searches/smartlists in the result)
        @Query("show_dupes") showDupes: Int = 1, // 0, 1 (if true if true ignore 'api_hide_dupe_searches' setting)
    ): PlaylistsResponse // TODO remove default values

    @GET("json.server.php?action=playlist")
    suspend fun getPlaylist(
        @Query("auth") authKey: String,
        @Query("filter") filter: String,
    ): PlaylistDto

    @GET("json.server.php?action=artist")
    suspend fun getArtistInfo(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = 0,
        @Query("filter") artistId: String = "",
        @Query("offset") offset: Int = 0, ): ArtistDto

    @GET("json.server.php?action=album")
    suspend fun getAlbumInfo(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = 0,
        @Query("filter") albumId: String = "",
        @Query("offset") offset: Int = 0, ): AlbumDto

    @GET("json.server.php?action=artist_albums")
    suspend fun getAlbumsFromArtist(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = 0,
        @Query("filter") artistId: String = "",
        @Query("offset") offset: Int = 0, ): AlbumsResponse

    @GET("json.server.php?action=album_songs")
    suspend fun getSongsFromAlbum(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = 0,
        @Query("filter") albumId: String = "",
        @Query("offset") offset: Int = 0, ): SongsResponse

    @GET("json.server.php?action=playlist_songs")
    suspend fun getSongsFromPlaylist(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = 0,
        @Query("random") random: Int = 0, // integer 0, 1 (if true get random songs using limit)
        @Query("filter") albumId: String = "",
        @Query("offset") offset: Int = 0, ): SongsResponse

    @GET("json.server.php?action=stats")
    suspend fun getSongsStats(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = NETWORK_REQUEST_LIMIT_HOME,
        //@Query("user_id") userId: Int,
        @Query("username") username: String? = null,
        @Query("type") _type: Type = Type.song,
        @Query("filter") filter: StatFilter,
        @Query("offset") offset: Int = 0, ): SongsResponse

    @GET("json.server.php?action=stats")
    suspend fun getAlbumsStats(
        @Query("auth") authKey: String,
        @Query("limit") limit: Int = NETWORK_REQUEST_LIMIT_HOME,
        //@Query("user_id") userId: Int,
        @Query("username") username: String? = null,
        @Query("type") _type: Type = Type.album,
        @Query("filter") filter: StatFilter,
        @Query("offset") offset: Int = 0
    ): AlbumsResponse

    @GET("json.server.php?action=flag")
    suspend fun flag( // flagged = favourites
        @Query("auth") authKey: String,
        @Query("id") id: String,
        @Query("flag") flag: Int,
        @Query("type") type: Type): SuccessResponse

    @GET("json.server.php?action=playlist_add_song")
    suspend fun addSongToPlaylist(
        @Query("auth") authKey: String,
        @Query("filter") playlistId: String,
        @Query("song") songId: String,
        @Query("check") check: Int = 1
    ): SuccessResponse

    @GET("json.server.php?action=playlist_remove_song")
    suspend fun removeSongFromPlaylist(
        @Query("auth") authKey: String,
        @Query("filter") playlistId: String,
        @Query("song") songId: String
    ): SuccessResponse

    @GET("json.server.php?action=playlist_create")
    suspend fun createNewPlaylist(
        @Query("auth") authKey: String,
        @Query("name") name: String,
        @Query("type") playlistType: PlaylistType
    ): PlaylistDto

    @GET("json.server.php?action=playlist_delete")
    suspend fun deletePlaylist(
        @Query("auth") authKey: String,
        @Query("filter") playlistId: String
    ): SuccessResponse

    @GET("json.server.php?action=playlist_edit")
    suspend fun editPlaylist(
        @Query("auth") authKey: String,
        @Query("filter") playlistId: String,
        @Query("owner") owner: String? = null, // Change playlist owner to the user id (-1 = System playlist)
        @Query("items") items: String? = null, // comma-separated song_id's (replaces existing items with a new id)
        @Query("tracks") tracks: String? = null, // comma-separated playlisttrack numbers matched to 'items' in order
        @Query("name") name: String? = null,
        @Query("type") playlistType: PlaylistType
    ): SuccessResponse

    /**
     * rate
     * Rates a library item
     *
     *  Input   Type        Description	                                    Optional
     * 'type'	string	    song, album, artist, playlist, podcast, ...     NO
     * 'id'	    integer	    library item id	                                NO
     * 'rating'	integer	    rating between 0-5	                            NO
     */
    @GET("json.server.php?action=rate")
    suspend fun rate(
        @Query("auth") authKey: String,
        @Query("id") itemId: String,
        @Query("rating") rating: Int,
        @Query("type") type: Type
    ): SuccessResponse

    /**
     * genres
     * This returns the genres (Tags) based on the specified filter
     *
     * Input	Type	Description	Optional
     * 'filter'	string	Filter results to match this string	YES
     * 'exact'	boolean	0, 1 (if true filter is exact = rather than fuzzy LIKE)	YES
     * 'offset'	integer	Return results starting from this index position	YES
     * 'limit'	integer	Maximum number of results to return	YES
     */
    @GET("json.server.php?action=genres")
    suspend fun getGenres(
        @Query("auth") authKey: String,
        @Query("filter") filter: String = "",
        @Query("exact") exact: Int = 0,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 0
    ): GenresResponse

    /**
     * genre_artists
     * This returns the artists associated with the genre in question as defined by the UID
     *
     * Input	Type	Description	Optional
     * 'filter'	string	UID of genre, returns artist JSON	YES
     * 'offset'	integer	Return results starting from this index position	YES
     * 'limit'	integer	Maximum number of results to return	YES
     */
    @GET("json.server.php?action=genre_artists")
    suspend fun getArtistsByGenre(
        @Query("auth") authKey: String,
        @Query("filter") filter: String = "",
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 0
    ): ArtistsResponse

    /**
     *
     * genre_songs
     * returns the songs for this genre
     *
     * Input	Type	Description	Optional
     * 'filter'	string	UID of genre, returns song JSON	YES
     * 'offset'	integer	Return results starting from this index position	YES
     * 'limit'	integer	Maximum number of results to return	YES
     * */
    @GET("json.server.php?action=genre_songs")
    suspend fun getSongsByGenre(
        @Query("auth") authKey: String,
        @Query("filter") genreId: String = "",
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = NETWORK_REQUEST_LIMIT_SONGS_BY_GENRE
    ): SongsResponse

    /**
     * Create a public url that can be used by anyone to stream media.
     * Takes the file id with optional description and expires parameters.
     *
     * 'filter'	string	UID of object you are sharing	NO
     * 'type'	string	object_type	NO
     * 'description'	string	description (will be filled for you if empty)	YES
     * 'expires'	integer	days to keep active	YES
     */
    @GET("json.server.php?action=share_create")
    suspend fun createShare(
        @Query("auth") authKey: String,
        @Query("filter") id: String,
        @Query("description") description: String = "",
        @Query("expires") expires: Int = 7,
        @Query("type") type: Type
    ): ShareDto

    /**
     * 'id'	integer	$object_id	NO
     * 'type'	string	song, podcast_episode, search, playlist	NO
     * 'format'	string	mp3, ogg, raw, etc (raw returns the original format)	YES
     */
    @Streaming
    @GET("json.server.php?action=download")
    suspend fun downloadSong(
        @Query("auth") authKey: String,
        @Query("id") songId: String,
        @Query("type") type: Type = Type.song, // song, podcast_episode, search, playlist
        @Query("format") format: String = "raw", // mp3, ogg, raw, etc (raw returns the original format)
    ): Response<ResponseBody>

    @POST
    suspend fun sendErrorReport(@Url url: String = BuildConfig.URL_ERROR_LOG, @Body body: String)

    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val BASE_URL = "http://localhost/"

        /**
         * get baseUrl from musicDatabase.dao.getCredentials()?.serverUrl
         */
        fun buildServerUrl(baseUrl: String): String {
            val sb = StringBuilder()
            // check if url starts with http or https, if not start the string builder with that
            if (!baseUrl.startsWith("http://") &&
                !baseUrl.startsWith("https://")) {
                sb.append("https://")
            }
            // add the url
            sb.append(baseUrl)
            // check if url contains server, if not add it
            if (!baseUrl.contains("/server")) {
                sb.append("/server")
            }

            return sb.toString()
        }
    }

    enum class Type(value: String) {
        song("song"),
        album("album"),
        artist("artist"),
        playlist("playlist")
    }

    enum class StatFilter(value: String) {
        random("random"),
        recent("recent"),
        newest("newest"),
        frequent("frequent"),
        flagged("flagged"),
        forgotten("forgotten"),
        highest("highest")
    }
}
