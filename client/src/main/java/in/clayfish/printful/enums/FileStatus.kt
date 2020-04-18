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

package `in`.clayfish.printful.enums

/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
enum class FileStatus {
    /**
     * After you add a file, it is saved with the status waiting and downloaded and processed later
     */
    WAITING,

    /**
     * the status is changed to ok if the file was loaded successfully and was a valid image file
     */
    OK,

    /**
     * if the process did not succeed
     */
    FAIL;

    override fun toString() = name.toLowerCase()

    companion object {
        /**
         * @param searchTerm The term to search status for
         * @return Found FileStatus
         */
        fun find(searchTerm: String?) = if (searchTerm == null || searchTerm.isEmpty()) null
        else valueOf(searchTerm.trim { it <= ' ' }.toUpperCase())
    }
}
