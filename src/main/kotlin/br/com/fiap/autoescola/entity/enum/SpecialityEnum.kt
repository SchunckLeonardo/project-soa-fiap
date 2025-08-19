package br.com.fiap.autoescola.entity.enum

enum class SpecialityEnum {
    EMPTY;

    companion object {
        fun getSpeciality(speciality: String?): SpecialityEnum {
            if (speciality.isNullOrBlank()) {
                return EMPTY
            }

            return runCatching {
                SpecialityEnum.valueOf(speciality)
            }.onFailure {
                EMPTY
            }.getOrThrow()
        }
    }

}