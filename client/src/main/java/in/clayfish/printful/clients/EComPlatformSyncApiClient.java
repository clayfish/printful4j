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
import in.clayfish.printful.enums.SyncStatus;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.models.SyncProduct;
import in.clayfish.printful.models.SyncVariant;
import in.clayfish.printful.models.info.SyncProductInfo;
import in.clayfish.printful.models.info.SyncVariantInfo;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/sync
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class EComPlatformSyncApiClient extends SimpleClient {
    private final static String TAG = EComPlatformSyncApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey YOUR_API_KEY
     */
    public EComPlatformSyncApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey        YOUR_API_KEY
     * @param configuration configuration object
     */
    public EComPlatformSyncApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(String externalVariantId) {
        externalVariantId = LibUtils.curateExternalId(externalVariantId);
        try {
            String response = LibUtils.createConnection(base64Key, "sync/variant/" + externalVariantId, configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createSyncVariantInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(long variantId) {
        try {
            String response = LibUtils.createConnection(base64Key, "sync/variant/" + variantId, configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createSyncVariantInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(String externalVariantId, SyncVariant syncVariant) {
        externalVariantId = LibUtils.curateExternalId(externalVariantId);
        try {
            String response = LibUtils.createConnection(base64Key, "sync/variant/" + externalVariantId, configuration)
                    .method(Connection.Method.PUT)
                    .requestBody(LibUtils.gson.toJson(syncVariant))
                    .execute().body();
            return createSyncVariantInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(long variantId, SyncVariant syncVariant) {
        try {
            String response = LibUtils.createConnection(base64Key, "sync/variant/" + variantId, configuration)
                    .method(Connection.Method.PUT)
                    .requestBody(LibUtils.gson.toJson(syncVariant))
                    .execute().body();
            return createSyncVariantInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "sync/variant/" + externalId, configuration)
                    .execute().body();
            return createSyncVariantInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(long id) {
        try {
            String response = LibUtils.createConnection(base64Key, "sync/variant/" + id, configuration)
                    .execute().body();
            return createSyncVariantInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "sync/products/" + externalId, configuration)
                    .method(Connection.Method.DELETE)
                    .execute().body();
            return createSyncProductInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(long id) {
        try {
            String response = LibUtils.createConnection(base64Key, "sync/products/" + id, configuration)
                    .method(Connection.Method.DELETE)
                    .execute().body();
            return createSyncProductInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "sync/products/" + externalId, configuration)
                    .execute().body();
            return createSyncProductInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(long id) {
        try {
            String response = LibUtils.createConnection(base64Key, "sync/products/" + id, configuration)
                    .execute().body();
            return createSyncProductInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<SyncProduct> getListOfSyncProducts(SyncStatus status, int offset, int limit) {
        Connection connection = LibUtils.createConnection(base64Key, "sync/products", configuration);

        if (status != null) {
            connection.data("status", status.toString());
        }

        if (offset > 0) {
            connection.data("offset", String.valueOf(offset));
        }

        if (limit > 0) {
            if (limit > 100) {
                limit = 100;
            }
            connection.data("limit", String.valueOf(limit));
        }

        try {
            String response = connection.execute().body();
            Type type = new TypeToken<Response<SyncProduct>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param response body of the response obtained from API
     * @return A response object for SyncVariantInfo
     */
    private Response<SyncVariantInfo> createSyncVariantInfo(String response) {
        Type type = new TypeToken<Response<SyncVariantInfo>>() {
        }.getType();
        return LibUtils.gson.fromJson(response, type);
    }

    /**
     * @param response body of the response obtained from API
     * @return A response object for SyncProductInfo
     */
    private Response<SyncProductInfo> createSyncProductInfo(String response) {
        Type type = new TypeToken<Response<SyncProductInfo>>() {
        }.getType();
        return LibUtils.gson.fromJson(response, type);
    }
}
