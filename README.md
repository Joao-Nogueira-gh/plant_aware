# Plant Aware

## Team<br>
* Tiago Melo 89005; Team Leader & Product Owner<br>
* Pedro Marques 89069; Architect<br>
* José Frias 89206; DevOps Master<br>
* João Nogueira 89262; DevOps Master<br>

## Backlog<br>
Pivotal Tracker : https://www.pivotaltracker.com/n/projects/2410513

## To Run

# Starting RabbitMQ
cd rmq
docker-compose up

# In a new terminal, start MongoDB
cd ../mongo
docker run 27016:27017 mongo

# In a new terminal, run the App
docker run -p 8080:8080 tiagoleonmelo/plant_aware
