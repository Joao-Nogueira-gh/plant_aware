package ua.deti.plant_aware;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mongodb.ClientSessionOptions;
import com.mongodb.ReadPreference;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.SessionScoped;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.CloseableIterator;

import ua.deti.plant_aware.model.Plant;
import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.model.*;
import ua.deti.plant_aware.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;


public class PlantRepositoryImpl extends MongoTemplate implements PlantRepository {

    public PlantRepositoryImpl(MongoDbFactory mongoDbFactory) {
        super(mongoDbFactory);
        // TODO Auto-generated constructor stub
    }

	@Override
	public <S extends Plant> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Plant> Iterable<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Plant> Iterable<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Plant> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Plant> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Plant> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public void handle(String message) {
        
        // Converting message from JSON to a HashMap
        HashMap<String, Object> hm = new HashMap<>(Utility.jsonToMap(message));
        System.out.println(hm);

        // Constructing the object that will be saved
        Plant plant = new Plant("Tulipa");
        plant.setSoil((double) hm.get("soil"));
        plant.setTemp((double) hm.get("temp"));
        plant.setWind((int) hm.get("wind"));
        plant.setId(((Integer) hm.get("plant_id")).longValue());


        // Deciding if this a CREATE or UPDATE
        if(!this.exists(new Query(where("plant_id").is(plant.getId())), "main"))
        {
            this.dropCollection("main");
            System.out.println("Saving " + this.insert(plant));
        }
        else
        {
            System.out.println("ALREADY EXISTS");
        }

    }

    

}
