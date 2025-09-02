package br.com.fiap.autoescola.entity.enum

enum class SpecialityEnum {
    MOTORCYCLE,
    CAR,
    TRUCK,
    BUS,
    EMPTY;

    companion object {
        fun getSpeciality(speciality: String?): SpecialityEnum {
            if (speciality.isNullOrBlank()) {
                return EMPTY
            }

            return runCatching {
                SpecialityEnum.valueOf(speciality.uppercase())
            }.onFailure {
                EMPTY
            }.getOrThrow()
        }
    }

}