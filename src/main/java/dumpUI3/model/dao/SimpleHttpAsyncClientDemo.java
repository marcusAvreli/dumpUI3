package dumpUI3.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dumpUI3.model.dao.ReportDAO;
import dumpUI3.model.entity.Report;

/** 
 * HttpAsyncClient simulates a simple example of post request
 * 
 * @author arron
 * @date November 1, 2015 2:23:18 PM 
 * @version 1.0 
 */
public class SimpleHttpAsyncClientDemo {
	private static final Logger logger = LoggerFactory.getLogger(SimpleHttpAsyncClientDemo.class);
	/**
	 * Set to trust custom certificates
	 * 	
	 * @param keyStorePath keystore path
	 * @param keyStorepass keystore password
	 * @return
	 */
	public static SSLContext custom(String keyStorePath, String keyStorepass) {
		SSLContext sc = null;
		FileInputStream instream = null;
		KeyStore trustStore = null;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = new FileInputStream(new File(keyStorePath));
			trustStore.load(instream, keyStorepass.toCharArray());
			//Trust your own CA and all self-signed certificates
			sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
		} catch (KeyStoreException | NoSuchAlgorithmException| CertificateException | IOException | KeyManagementException e) {
			e.printStackTrace();
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
			}
		}
		return sc;
	}
	
	/**
	 * Bypass verification
	 * 	
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		//Implement an X509TrustManager interface to bypass authentication without modifying the methods inside
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		sc.init(null, new TrustManager[] {trustManager }, null);
		return sc;
	}

	/**
	 * Set up proxy
	 * @param builder
	 * @param hostOrIP
	 * @param port
	 */
	public static HttpAsyncClientBuilder proxy(String hostOrIP, int port){
		//followed by proxy address, proxy port number, and protocol type  
		HttpHost proxy = new HttpHost(hostOrIP, port, "http");  
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		return HttpAsyncClients.custom().setRoutePlanner(routePlanner);
	}
	
	/**
	 * Simulation request
	 * 
	 * @param url resource address
	 * @param map parameter list
	 * @param encoding
	 * @param handler result processing class
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void send(String url, Map<String,Object> map,final String encoding, final AsyncHandler handler) throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {

		//Bypass certificate verification and process https request
		SSLContext sslcontext = createIgnoreVerifySSL();
		
       //Set the object that handles the socket link factory corresponding to the protocol http and https
		Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .register("https", new SSLIOSessionStrategy(sslcontext))
                .build();
		//Configure io thread
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
		//Set the connection pool size
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        //PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor, null, sessionStrategyRegistry, null);
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(100);
        
       //Create a custom httpclient object
//		final CloseableHttpAsyncClient client = proxy("127.0.0.1", 8087).setConnectionManager(connManager).build();
final CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
		
		//Create a post request object
HttpPost httpPost = new HttpPost(url);
		
		//Loading parameters
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		Report report = (Report) map.get("report");
		 ObjectMapper mapper = new ObjectMapper();
         try {
             String json = mapper.writeValueAsString(report);
             StringEntity requestEntity = new StringEntity(
            		 json,
            		    ContentType.APPLICATION_JSON);
             httpPost.setEntity(requestEntity);
             System.out.println("ResultingJSONstring = " + json);
             //System.out.println(json);
         } catch (JsonProcessingException e) {
             e.printStackTrace();
         }
		//Set the parameters to the request object
		

		logger.info("Request address:"+url);
		logger.info("Request parameters:"+nvps.toString());
		
		//Set header information
		//Specify the message header [Content-type], [User-Agent]
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("Accept-type", "application/json");
		
		//Start the client
		client.start();
		//Perform the requested operation and get the result (asynchronous)
		client.execute(httpPost, new FutureCallback<HttpResponse>() {
			
			@Override
			public void failed(Exception ex) {
				handler.failed(ex);
				close(client);
			}
			
			@Override
			public void completed(HttpResponse resp) {
				String body="";
				//When EntityUtils.toString() is used here, there will be a high probability of reporting an error. Reason: not accepted, the link is closed
				try {
					HttpEntity entity = resp.getEntity();
					if (entity != null) {
						final InputStream instream = entity.getContent();
						try {
							final StringBuilder sb = new StringBuilder();
							final char[] tmp = new char[1024];
							final Reader reader = new InputStreamReader(instream, encoding);
							int l;
							while ((l = reader.read(tmp)) != -1) {
								sb.append(tmp, 0, l);
							}
							body = sb.toString();
						} finally {
							instream.close();
							EntityUtils.consume(entity);
						}
					}
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
				handler.completed(body);
				close(client);
			}
			
			@Override
			public void cancelled() {
				handler.cancelled();
				close(client);
			}
		});
	}
	
	/**
	 * Close the client object
	 * 
	 * @param client
	 */
	private static void close(CloseableHttpAsyncClient client) {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static class AsyncHandler implements IHandler{
		
		@Override
		public Object failed(Exception e) {
			System.err.println(Thread.currentThread().getName()+"--Failed--"+e.getClass().getName()+"--"+e.getMessage());
			return null;
		}
		@Override
		public Object completed(String respBody) {
			System.out.println(Thread.currentThread().getName()+"--Get content:"+respBody);
			return null;
		}
		@Override
		public Object cancelled() {
			System.out.println(Thread.currentThread().getName()+"--Canceled");
			return null;
		}
	}
	
	/**
	 * Callback processing interface
	 * 
	 * @author arron
	 * @date November 10, 2015 at 10:05:40 AM 
	 * @version 1.0
	 */
	public interface IHandler {
		
		/**
		 * When handling exceptions, execute this method
		 * @return
		 */
		Object failed(Exception e);
		
		/**
		 * When processing is normal, execute this method
		 * @return
		 */
		Object completed(String respBody);
		
		/**
		 * When processing cancellation, execute this method
		 * @return
		 */
		Object cancelled();
	}
	
}
