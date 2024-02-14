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
package luci.sixsixsix.powerampache2.domain.models

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import luci.sixsixsix.powerampache2.common.Constants
import luci.sixsixsix.powerampache2.common.Constants.DOGMAZIC_FAKE_CITY
import luci.sixsixsix.powerampache2.common.Constants.DOGMAZIC_FAKE_NAME
import luci.sixsixsix.powerampache2.common.Constants.DOGMAZIC_FAKE_STATE
import luci.sixsixsix.powerampache2.common.Constants.DOGMAZIC_FAKE_USERNAME
import luci.sixsixsix.powerampache2.common.Constants.MASTODON_URL

@Parcelize
data class User(
    val id: String,
    val username: String,
    val email: String,
    val access: Int,
    val streamToken: String? = null,
    val fullNamePublic: Int,
    val fullName: String? = null,
    //val validation: Any? = null,
    val disabled: Boolean,
    val createDate: Int = Constants.ERROR_INT,
    val lastSeen: Int = Constants.ERROR_INT,
    val website: String,
    val state: String,
    val city: String
): Parcelable {
    companion object {
        fun emptyUser(): User = User(
            "", "", "",
            Constants.ERROR_INT,
            null,
            Constants.ERROR_INT,
            null,
            true,
            Constants.ERROR_INT,
            Constants.ERROR_INT,
            "",
            "",
            ""
        )

        fun demoUser(): User = User(
            id = "demoUser",
            username = DOGMAZIC_FAKE_USERNAME,
            email = Constants.DOGMAZIC_FAKE_EMAIL,
            access = Constants.ERROR_INT,
            streamToken = null,
            fullNamePublic = 1,
            fullName = DOGMAZIC_FAKE_NAME,
            disabled = false,
            createDate = Constants.ERROR_INT,
            lastSeen = Constants.ERROR_INT,
            website = MASTODON_URL,
            state = DOGMAZIC_FAKE_STATE,
            city = DOGMAZIC_FAKE_CITY
        )

        fun mockUser(): User = Gson().fromJson(
            "{\n" +
                "    \"id\": \"3\",\n" +
                "    \"username\": \"luci\",\n" +
                "    \"auth\": null,\n" +
                "    \"email\": \"some@er.fd\",\n" +
                "    \"access\": 25,\n" +
                "    \"streamtoken\": null,\n" +
                "    \"fullname_public\": 1,\n" +
                "    \"validation\": null,\n" +
                "    \"disabled\": false,\n" +
                "    \"create_date\": 1704516888,\n" +
                "    \"last_seen\": 1706202621,\n" +
                "    \"website\": \"http://somewebsite.mockd\",\n" +
                "    \"state\": \"Mercury\",\n" +
                "    \"city\": \"Phobos Town\",\n" +
                "    \"fullname\": \"Lucifer The Conqueror\"\n" +
                "}",
            User::class.java
        )
    }
}
