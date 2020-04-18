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
import in.clayfish.printful.enums.FileStatus;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Response;
import in.clayfish.printful.utils.LibUtils;

/**
 * See https://www.theprintful.com/docs/files
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class FileLibraryApiClient extends SimpleClient {
    private final static String TAG = FileLibraryApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public FileLibraryApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public FileLibraryApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<File> getFileInfo(int fileId) {
        try {
            String response = LibUtils.createConnection(base64Key, "files/" + fileId, configuration)
                    .execute().body();
            Type type = new TypeToken<Response<File>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<File> addANewFile(File file) {
        try {
            String response = LibUtils.createConnection(base64Key, "files", configuration)
                    .method(Connection.Method.POST).requestBody(LibUtils.gson.toJson(file))
                    .execute().body();
            Type type = new TypeToken<Response<File>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<File> getListOfFiles(FileStatus status, int offset, int limit) {
        try {
            Connection connection = LibUtils.createConnection(base64Key, "files", configuration);

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

            Type type = new TypeToken<Response<File>>() {
            }.getType();
            return LibUtils.gson.fromJson(connection.execute().body(), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
