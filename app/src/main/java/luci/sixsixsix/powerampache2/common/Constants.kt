package luci.sixsixsix.powerampache2.common

import luci.sixsixsix.powerampache2.domain.models.Song

object Constants {
    const val SONG_COLLECTION = "songs"
    const val MEDIA_ROOT_ID = "root_id"
    const val NETWORK_ERROR = "NETWORK_ERROR"
    const val UPDATE_PLAYER_POSITION_INTERVAL = 100L
    const val NOTIFICATION_CHANNEL_ID = "music"
    const val NOTIFICATION_ID = 1

    const val TIMEOUT_CONNECTION_S = 120L
    const val TIMEOUT_READ_S = 120L
    const val TIMEOUT_WRITE_S = 120L

    const val ERROR_INT = -1
    const val NETWORK_REQUEST_LIMIT_DEBUG = 222 // TODO remove or set to zero for production

}
