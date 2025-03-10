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
package luci.sixsixsix.powerampache2.common

import luci.sixsixsix.powerampache2.BuildConfig

object Constants {
    // LOCAL DB
    const val DB_LOCAL_NAME = "musicdb.db"
    const val DATABASE_VERSION = 82

    const val DB_EMPTY_ATTRIBUTE = "{\"attr\":[]}" // "{\"attr\":[{\"id\":\"209\",\"name\":\"Overkill\"}]}

    // NETWORK
    const val TIMEOUT_CONNECTION_S = 15L
    const val TIMEOUT_READ_S = 60L
    const val TIMEOUT_WRITE_S = 60L
    const val NETWORK_REQUEST_LIMIT_HOME = 40
    const val NETWORK_REQUEST_LIMIT_SONGS_BY_GENRE = 40
    const val DATABASE_LIMIT_ELEMENTS = 66
    const val QUICK_PLAY_MIN_SONGS = 50
    const val USER_ACCESS_DEFAULT = 25
    const val USER_EMAIL_DEFAULT = "" //""No@Email.Present"
    const val USER_FULL_NAME_PUBLIC_DEFAULT = 0
    const val PLAYLIST_FETCH_LIMIT = 50

    // TIMEOUTS (non-network)
    const val LOCAL_SCROBBLE_TIMEOUT_MS = 20000L
    const val PLAY_LOAD_TIMEOUT = 10000L
    const val LOGIN_SCREEN_TIMEOUT = 1500L
    const val HOME_LOADING_TIMEOUT = 1000L
    const val SERVICE_STOP_TIMEOUT = 2000L
    const val SEARCH_TIMEOUT = 1200L    // allow user to type before starting search


    // ERROR CONSTANTS
    const val NOT_IMPLEMENTED_USER_ID = "666"
    const val ERROR_INT = -1
    const val ERROR_FLOAT = ERROR_INT.toFloat()
    const val ERROR_STRING = "ERROR"
    const val LOADING_STRING = "LOADING"
    const val USER_ID_ERROR = ERROR_INT

    // FLAGS
    const val RESET_QUEUE_ON_NEW_SESSION = BuildConfig.RESET_QUEUE_ON_NEW_SESSION
    const val CLEAR_TABLE_AFTER_FETCH = false
    const val ALWAYS_FETCH_ALL_PLAYLISTS = true

    // DEBUG VALUES
    const val NETWORK_REQUEST_LIMIT_DEBUG = 20
    const val ERROR_TITLE = ERROR_STRING

    // DONATION LINKS
    const val DONATION_BITCOIN_ADDRESS = "bc1qm9dvdrukgrqpg5f7466u4cy7tfvwcsc8pqshl4"
    const val DONATION_BITCOIN_URI = "bitcoin:$DONATION_BITCOIN_ADDRESS"
    const val DONATION_PAYPAL_URI = "https://paypal.me/powerampache"

    // URLs
    const val GITHUB_IMG_URL = "https://s1.ax1x.com/2023/01/12/pSu1a36.png"
    const val GITHUB_URL = "https://github.com/icefields/Power-Ampache-2"
    const val GPLV3_IMG_URL = "https://www.gnu.org/graphics/gplv3-with-text-136x68.png"
    const val GPLV3_URL = "https://github.com/icefields/Power-Ampache-2/blob/main/LICENSE.md"
    const val TELEGRAM_IMG_URL = "https://static-00.iconduck.com/assets.00/telegram-icon-512x512-4sztjer8.png"
    const val TELEGRAM_URL = "https://t.me/PowerAmpache"
    const val MASTODON_IMG_URL = "https://github.com/icefields/Power-Ampache-2/assets/149625124/81e35dc2-d81c-46ed-9321-4461349dc8e7"
    const val MASTODON_URL = "https://floss.social/@powerampache"

    const val DOGMAZIC_FAKE_EMAIL = "floss.social/@PowerAmpache"
    const val DOGMAZIC_FAKE_NAME = "Draven Wilhelmine"
    const val DOGMAZIC_FAKE_USERNAME = "PowerAmpache"
    const val DOGMAZIC_FAKE_STATE = "Ehime"
    const val DOGMAZIC_FAKE_CITY = "Aoshima"
}
