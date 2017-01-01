package in.clayfish.printful.clients;


import com.google.gson.reflect.TypeToken;

import org.apache.commons.codec.binary.Base64;
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
        this.base64Key = Base64.encodeBase64String(apiKey.getBytes());
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
