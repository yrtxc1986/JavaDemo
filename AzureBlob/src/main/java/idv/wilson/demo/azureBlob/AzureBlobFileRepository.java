package idv.wilson.demo.azureBlob;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.function.Function;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Service
public class AzureBlobFileRepository implements FileRepository {

	@Autowired
	private Function<String, CloudBlobContainer> blobContainerFactory;
	@Autowired
	private Logger log;

	@Override
	public boolean save(String CompanyID, String fileID, InputStream file, long fileSize) {
		CloudBlobContainer container = blobContainerFactory.apply(CompanyID);
		try {
			CloudBlockBlob blob = container.getBlockBlobReference(fileID);
			blob.upload(file, fileSize);
		} catch (URISyntaxException | StorageException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void get(String CompanyID, String fileID, OutputStream file) {
		CloudBlobContainer container = blobContainerFactory.apply(CompanyID);
		try {
			CloudBlockBlob blob = container.getBlockBlobReference(fileID);
			blob.download(file);
		} catch (URISyntaxException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(String CompanyID, String fileID) {
		CloudBlobContainer container = blobContainerFactory.apply(CompanyID);
		try {
			CloudBlockBlob blob = container.getBlockBlobReference(fileID);
			return blob.deleteIfExists();
		} catch (URISyntaxException | StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
