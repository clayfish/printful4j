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

package in.clayfish.printful.clients;

import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.info.WebhookInfo;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/webhooks
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class WebhookApiClient extends SimpleClient {
    private final static String TAG = WebhookApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey Your API key
     */
    public WebhookApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey        YOUR_API_KEY
     * @param configuration Configuration object
     */
    public WebhookApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<WebhookInfo> getWebhookConfig() {
        try {
            String response = LibUtils.createConnection(base64Key, "webhooks", configuration).execute().body();
            // TODO inspect response to fix WebhookInfo model
            return createWebhookInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo) {
        try {
            String response = LibUtils.createConnection(base64Key, "webhooks", configuration)
                    .method(Connection.Method.POST).requestBody(LibUtils.gson.toJson(webhookInfo))
                    .execute().body();
            return createWebhookInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<WebhookInfo> disableWebhookSupport() {
        try {
            String response = LibUtils.createConnection(base64Key, "webhooks", configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createWebhookInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param response obtained string from API
     * @return response object
     */
    private Response<WebhookInfo> createWebhookInfo(String response) {
        Type type = new TypeToken<Response<WebhookInfo>>() {
        }.getType();
        return LibUtils.gson.fromJson(response, type);
    }

}
