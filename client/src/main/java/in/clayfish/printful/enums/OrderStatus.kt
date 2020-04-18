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
 * See https://www.theprintful.com/docs/orders
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
enum class OrderStatus {
    /**
     * The order is created but is not yet submitted for fulfillment. You still can edit it and
     * confirm later.
     */
    DRAFT,

    /**
     * The order has been submitted for fulfillment, but is not yet accepted for fulfillment. You
     * can still cancel the order if you need.
     */
    PENDING,

    /**
     * Order was submitted for fulfillment but was returned for review because of an error (problem
     * with address, missing printfiles, charging has failed, etc.).
     */
    FAILED,

    /**
     * The order has been canceled and can no longer be processed. If the order was charged then
     * the amount has been returned to your credit card.
     */
    CANCELED,

    /**
     * The order is being fulfilled and can no longer be cancelled or modified. Contact customer
     * support if there are any issues with the order at this point.
     */
    IN_PROCESS,

    /**
     * The order has encountered a problem during the fulfillment that needs to be resolved
     * together with Printful customer service before fulfillment can continue.
     */
    ON_HOLD,

    /**
     * The order is partially fulfilled (some items are shipped already, the rest will follow)
     */
    PARTIAL,

    /**
     * All items have been shipped successfully
     */
    FULFILLED;

    override fun toString() = name.toLowerCase()

    companion object {
        /**
         * This is the easiest way to loosely find an OrderStatus object for a string
         *
         * @param term Term to search status with
         * @return Found OrderStatus
         */
        fun find(term: String?) = if (term == null || term.isEmpty()) null
        else valueOf(term.trim { it <= ' ' }.toUpperCase())
    }
}
