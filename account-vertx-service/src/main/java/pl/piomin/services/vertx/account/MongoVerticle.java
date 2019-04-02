package pl.piomin.services.vertx.account;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.serviceproxy.ProxyHelper;
import pl.piomin.services.vertx.account.data.AccountRepository;
import pl.piomin.services.vertx.account.data.AccountRepositoryImpl;

public class MongoVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		JsonObject config = new JsonObject();
//		o.put("host", "192.168.99.100");
//		o.put("port", 27017);
//		o.put("db_name", "test");
		config.put("connection_string", "mongodb://micro:micro@mongodb/microdb");
		final MongoClient client = MongoClient.createShared(vertx, config);
		final AccountRepository service = new AccountRepositoryImpl(client);
		ProxyHelper.registerService(AccountRepository.class, vertx, service, "account-service");
	}
	
}
