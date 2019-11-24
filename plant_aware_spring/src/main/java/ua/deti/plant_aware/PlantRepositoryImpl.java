package ua.deti.plant_aware;

import java.util.Collection;
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
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoOperations;
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

public class PlantRepositoryImpl implements PlantRepository {

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
    public String getCollectionName(Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document executeCommand(String jsonCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document executeCommand(Document command) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document executeCommand(Document command, ReadPreference readPreference) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void executeQuery(Query query, String collectionName, DocumentCallbackHandler dch) {
        // TODO Auto-generated method stub

    }

    @Override
    public <T> T execute(DbCallback<T> action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T execute(Class<?> entityClass, CollectionCallback<T> action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T execute(String collectionName, CollectionCallback<T> action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SessionScoped withSession(ClientSessionOptions sessionOptions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MongoOperations withSession(ClientSession session) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> CloseableIterator<T> stream(Query query, Class<T> entityType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> CloseableIterator<T> stream(Query query, Class<T> entityType, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MongoCollection<Document> createCollection(Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MongoCollection<Document> createCollection(Class<T> entityClass, CollectionOptions collectionOptions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MongoCollection<Document> createCollection(String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MongoCollection<Document> createCollection(String collectionName, CollectionOptions collectionOptions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> getCollectionNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MongoCollection<Document> getCollection(String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> boolean collectionExists(Class<T> entityClass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean collectionExists(String collectionName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> void dropCollection(Class<T> entityClass) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dropCollection(String collectionName) {
        // TODO Auto-generated method stub

    }

    @Override
    public IndexOperations indexOps(String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IndexOperations indexOps(Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BulkOperations bulkOps(BulkMode mode, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BulkOperations bulkOps(BulkMode mode, Class<?> entityType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BulkOperations bulkOps(BulkMode mode, Class<?> entityType, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> AggregationResults<O> aggregate(TypedAggregation<?> aggregation, String collectionName,
            Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> AggregationResults<O> aggregate(TypedAggregation<?> aggregation, Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> AggregationResults<O> aggregate(Aggregation aggregation, Class<?> inputType, Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> AggregationResults<O> aggregate(Aggregation aggregation, String collectionName, Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> CloseableIterator<O> aggregateStream(TypedAggregation<?> aggregation, String collectionName,
            Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> CloseableIterator<O> aggregateStream(TypedAggregation<?> aggregation, Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> CloseableIterator<O> aggregateStream(Aggregation aggregation, Class<?> inputType, Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <O> CloseableIterator<O> aggregateStream(Aggregation aggregation, String collectionName,
            Class<O> outputType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
            Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
            MapReduceOptions mapReduceOptions, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction,
            String reduceFunction, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction,
            String reduceFunction, MapReduceOptions mapReduceOptions, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> GeoResults<T> geoNear(NearQuery near, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> GeoResults<T> geoNear(NearQuery near, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findOne(Query query, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findOne(Query query, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean exists(Query query, String collectionName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean exists(Query query, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean exists(Query query, Class<?> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> List<T> find(Query query, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> find(Query query, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findById(Object id, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findById(Object id, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findDistinct(Query query, String field, Class<?> entityClass, Class<T> resultClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findDistinct(Query query, String field, String collectionName, Class<?> entityClass,
            Class<T> resultClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findAndModify(Query query, Update update, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findAndModify(Query query, Update update, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass,
            String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S, T> T findAndReplace(Query query, S replacement, FindAndReplaceOptions options, Class<S> entityType,
            String collectionName, Class<T> resultType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findAndRemove(Query query, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T findAndRemove(Query query, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count(Query query, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long count(Query query, String collectionName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long count(Query query, Class<?> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T> T insert(T objectToSave) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T insert(T objectToSave, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Collection<T> insert(Collection<? extends T> batchToSave, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Collection<T> insert(Collection<? extends T> batchToSave, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Collection<T> insertAll(Collection<? extends T> objectsToSave) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T save(T objectToSave) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T save(T objectToSave, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult upsert(Query query, Update update, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult upsert(Query query, Update update, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult upsert(Query query, Update update, Class<?> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult updateFirst(Query query, Update update, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult updateFirst(Query query, Update update, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult updateFirst(Query query, Update update, Class<?> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult updateMulti(Query query, Update update, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult updateMulti(Query query, Update update, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UpdateResult updateMulti(Query query, Update update, Class<?> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeleteResult remove(Object object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeleteResult remove(Object object, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeleteResult remove(Query query, Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeleteResult remove(Query query, Class<?> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeleteResult remove(Query query, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findAllAndRemove(Query query, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findAllAndRemove(Query query, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> findAllAndRemove(Query query, Class<T> entityClass, String collectionName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MongoConverter getConverter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> ExecutableFind<T> query(Class<T> domainType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> ExecutableInsert<T> insert(Class<T> domainType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> ExecutableUpdate<T> update(Class<T> domainType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> ExecutableRemove<T> remove(Class<T> domainType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> ExecutableAggregation<T> aggregateAndReturn(Class<T> domainType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> MapReduceWithMapFunction<T> mapReduce(Class<T> domainType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ScriptOperations scriptOps() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> GroupByResults<T> group(String inputCollectionName, GroupBy groupBy, Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> GroupByResults<T> group(Criteria criteria, String inputCollectionName, GroupBy groupBy,
            Class<T> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

}
