package idv.wilson.demo.azureBlob.config;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.function.Function;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

@Configuration
@PropertySource("classpath:azure.properties")
public class AzureConfig {

	@Autowired
	private Logger log;

	@Value("${storageConnectionString}")
	private String storageConnectionString;

	@Bean
	@Scope(value = "singleton")
	public CloudBlobClient blobClient() {
		log.debug(storageConnectionString);
		try {
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
			return blobClient;
		} catch (InvalidKeyException | URISyntaxException e) {
			log.error("Cannot Connect to Azure Blob", e);
		}
		return null;
	}

	@Bean
	public Function<String, CloudBlobContainer> blobContainerFactory() {
		return name -> blobContainer(name);
	}

	@Bean
	@Scope(value = "prototype")
	public CloudBlobContainer blobContainer(String name) {
		try {
			CloudBlobContainer container = blobClient().getContainerReference(name);
			// BlobRequestOptions blobRequestOptions = new BlobRequestOptions();
			// OperationContext operationContext = new OperationContext();
			// container.createIfNotExists(BlobContainerPublicAccessType.OFF,
			// blobRequestOptions, operationContext);
			return container;
		} catch (URISyntaxException | StorageException e) {
			log.error("Cannot get Blob Container", e);
		}
		return null;
	}

}
