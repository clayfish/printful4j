/*
 * MIT License
 *
 * Copyright (c) 2016-2019 ClayFish Technologies LLP
 * Copyright (c) 2020 ClayFish Technologies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package `in`.clayfish.printful.models.includable

import `in`.clayfish.printful.exceptions.ValidationFailedException

/**
 * @author shuklaalok7 (alok@clay.fish)
 * @since 2016-12-24
 */
class Address private constructor(
        /**
         * Full name
         */
        val name: String?,

        /**
         * Company name
         */
        val company: String?,

        /**
         * Address line 1
         */
        val address1: String?,

        /**
         * Address line 2
         */
        val address2: String?,
        val city: String?,
        val state: State?,
        val country: Country?,
        val zip: String?,
        val phone: String?,
        val email: String?
) {

    /**
     * @author shuklaalok7
     * @since 2/01/2017
     */
    data class Builder(
            var name: String? = null,
            var company: String? = null,
            var address1: String? = null,
            var address2: String? = null,
            var city: String? = null,
            var state: State? = null,
            var country: Country? = null,
            var zip: String? = null,
            var phone: String? = null,
            var email: String? = null
    ) : `in`.clayfish.printful.models.Builder<Address?> {
        private var checkValidity = false

        fun name(name: String?) = apply { this.name = name }
        fun company(company: String?) = apply { this.company = company }
        fun address1(address1: String?) = apply { this.address1 = address1 }
        fun address2(address2: String?) = apply { this.address2 = address2 }
        fun city(city: String?) = apply { this.city = city }
        fun state(state: State?) = apply { this.state = state }
        fun country(country: Country?) = apply { this.country = country }
        fun zip(zip: String?) = apply { this.zip = zip }
        fun phone(phone: String?) = apply { this.phone = phone }
        fun email(email: String?) = apply { this.email = email }

        override fun check() = apply { checkValidity = true }

        override fun build(): Address {
            if (checkValidity) checkValidity()
            return Address(name, company, address1, address2, city, state, country, zip, phone, email)
        }

        private fun checkValidity() {
            if (country == null || country?.code.isNullOrBlank() || state == null ||
                    state?.code.isNullOrBlank() || name.isNullOrBlank() || city.isNullOrBlank() ||
                    address1.isNullOrBlank() || zip.isNullOrBlank())
                throw ValidationFailedException()
        }
    }
}
