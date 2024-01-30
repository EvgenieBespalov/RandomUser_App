package com.example.user_list.binding.user_list.converter

import com.example.user_list.domain.entities.*
import ru.cft.shift2023winter.data.model.LocationModel
import ru.cft.shift2023winter.data.model.UserModel

class UserListConverter {
    fun convertUser(from: UserModel): User =
        User(
            gender = from.gender,
            name = Name(
                title = from.name.title,
                first = from.name.first,
                last = from.name.last
            ),
            location = Location(
                street = Street(
                    number = from.location.street.number,
                    name = from.location.street.name
                ),
                city = from.location.city,
                state = from.location.state,
                country = when(from.location.country){
                    "Ukraine" -> "Russia"
                    else -> from.location.country },
                postcode = from.location.postcode,
                coordinates = Coordinates(
                    latitude = from.location.coordinates.latitude,
                    longitude = from.location.coordinates.longitude
                ),
                timezone = Timezone(
                    offset = from.location.timezone.offset,
                    description = from.location.timezone.description
                )
            ),
            email = from.email,
            login = Login(
                uuid = from.login.uuid,
                username = from.login.username,
                password = from.login.password,
                salt = from.login.salt,
                md5 = from.login.md5,
                sha1 = from.login.sha1,
                sha256 = from.login.sha256
            ),
            dateOfBirth = DateOfBirth(
                date = from.dateOfBirth.date,
                age = from.dateOfBirth.age
            ),
            registered = Registered(
                date = from.registered.date,
                age = from.registered.age
            ),
            phone = from.phone,
            cell = from.cell,
            id = Id(
                name = from.id.name,
                value = from.id.value
            ),
            picture = Picture(
                large = from.picture.large,
                medium = from.picture.medium,
                thumbnail = from.picture.thumbnail
            ),
            nat = from.nat
        )
}