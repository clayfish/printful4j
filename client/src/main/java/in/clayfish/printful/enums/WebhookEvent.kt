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
 * See https://www.theprintful.com/docs/webhooks
 *
 * @author shuklaalok7
 * @since 1/01/2017
 */
enum class WebhookEvent {
    /**
     * Calls POST http://example.com/your-webhook-url
     * Is called when a shipment with all or part of the ordered items is shipped.
     * If the order is shipped in multiple packages, this event will be called for every package sent.
     * If some items are reshipped, a shipping notification will be sent again for the same items.
     */
    PACKAGE_SHIPPED,

    /**
     * Calls POST http://example.com/your-webhook-url
     * Is called when a confirmed order changes its status to [OrderStatus.FAILED].
     * It can happen if printfiles can not be downloaded, are not valid image files or when charging
     * of the payment card fails.
     */
    ORDER_FAILED,

    /**
     * Calls POST http://example.com/your-webhook-url
     * Is called when a confirmed order changes its status to [OrderStatus.CANCELED].
     * It can happen when a submitted order is canceled from the dashboard or through the API or
     * when the order is cancelled by the Printful staff.
     */
    ORDER_CANCELED,

    /**
     * Is called when a new product or variant is imported from store's e-commerce integration.
     *
     * @see in.clayfish.printful.clients.EComPlatformSyncApiClient
     */
    PRODUCT_SYNCED,

    /**
     * Is called when stock is updated for some of product's variants.
     * Contains product id and ids of it's discontinued variants and variants that are out of stock.
     * Variant ids that are not present should be considered as active and in stock.
     */
    STOCK_UPDATED;

    override fun toString() = name.toLowerCase()
}
