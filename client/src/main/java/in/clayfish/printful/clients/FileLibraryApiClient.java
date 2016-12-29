package in.clayfish.printful.clients;


import org.apache.commons.codec.binary.Base64;

import in.clayfish.printful.SimpleClient;
import in.clayfish.printful.enums.FileStatus;
import in.clayfish.printful.models.Configuration;
import in.clayfish.printful.models.File;
import in.clayfish.printful.models.Response;

/**
 * See https://www.theprintful.com/docs/files
 * TODO implement
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
        return super.getFileInfo(fileId);
    }

    @Override
    public Response<File> addANewFile(File file) {
        return super.addANewFile(file);
    }

    @Override
    public Response<File> getListOfFiles(FileStatus status, int offset, int limit) {
        return super.getListOfFiles(status, offset, limit);
    }
}
