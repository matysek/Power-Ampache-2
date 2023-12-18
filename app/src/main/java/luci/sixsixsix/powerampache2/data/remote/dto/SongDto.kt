package luci.sixsixsix.powerampache2.data.remote.dto

import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.domain.models.Song

data class SongDto(
    @SerializedName("album")
    val album: MusicAttributeDto,

    @SerializedName("album_mbid")
    val albumMbid: Any,

    @SerializedName("albumartist")
    val albumartist: MusicAttributeDto,

    @SerializedName("albumartist_mbid")
    val albumartistMbid: Any,

    @SerializedName("art")
    val art: String,

    @SerializedName("artist")
    val artist: MusicAttributeDto,

    @SerializedName("artist_mbid")
    val artistMbid: Any,

    @SerializedName("averagerating")
    val averagerating: Any,

    @SerializedName("bitrate")
    val bitrate: Int,

    @SerializedName("catalog")
    val catalog: Int,

    @SerializedName("channels")
    val channels: Int,

    @SerializedName("comment")
    val comment: Any,

    @SerializedName("composer")
    val composer: String,

    @SerializedName("disk")
    val disk: Int,

    @SerializedName("filename")
    val filename: String,

    @SerializedName("flag")
    val flag: Int,

    @SerializedName("genre")
    val genre: List<MusicAttributeDto>,

    @SerializedName("id")
    val id: String,

    @SerializedName("language")
    val language: Any,

    @SerializedName("license")
    val license: Any,

    @SerializedName("lyrics")
    val lyrics: Any,

    @SerializedName("mbid")
    val mbid: Any,

    @SerializedName("mime")
    val mime: String,

    @SerializedName("mode")
    val mode: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("playcount")
    val playcount: Int,

    @SerializedName("playlisttrack")
    val playlisttrack: Int,

    @SerializedName("preciserating")
    val preciserating: Any,

    @SerializedName("publisher")
    val publisher: Any,

    @SerializedName("r128_album_gain")
    val r128AlbumGain: Any,

    @SerializedName("r128_track_gain")
    val r128TrackGain: Any,

    @SerializedName("rate")
    val rate: Int,

    @SerializedName("rating")
    val rating: Any,

    @SerializedName("replaygain_album_gain")
    val replaygainAlbumGain: Any,

    @SerializedName("replaygain_album_peak")
    val replaygainAlbumPeak: Any,

    @SerializedName("replaygain_track_gain")
    val replaygainTrackGain: Any,

    @SerializedName("replaygain_track_peak")
    val replaygainTrackPeak: Any,

    @SerializedName("size")
    val size: Int?,

    @SerializedName("time")
    val time: Int?,

    @SerializedName("title")
    val title: String,

    @SerializedName("track")
    val track: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("year")
    val year: Int
)

data class SongsResponse(
    @SerializedName("song") val songs: List<SongDto>?,
    @SerializedName("error") val error: ErrorDto?
)

fun SongDto.toSong() = Song(
    mediaId = id,
    title = title,
    subtitle = artist.name,
    songUrl = url,
    imageUrl = art
)
