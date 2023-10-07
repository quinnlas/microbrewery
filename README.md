# Microbrewery
A project for learning Micronaut and Spring. A microservice architecture application that supports functions of a brewery such as serving customers, generating bills, and rotating a tap list. It's funny because it has micro in the name.

The project is inspired by https://github.com/mfarache/micronaut-ms.

## Features
### MVP Features
- ~~Customers can see what beer is available on the tap list.~~
- Customers can order beer.
- Customers can get a receipt after ordering multiple beers.
- Beers can be added or removed from the tap list.

### Stretch Features
- Testing.
- ~~Stock of each beer type is tracked.~~
- Taxes are calculated on receipts.
- Customers are given the option to tip their service staff.

## On Microservices
I am by no means an expert in microservices, these are just some points that have come up in my reading and make sense to me.

### Links
The "definitive article" on microservices:
https://martinfowler.com/articles/microservices.html

A helpful step-by-step sequence of articles for creating a microservice architecture application:
https://learn.microsoft.com/en-us/azure/architecture/microservices/model/domain-analysis 

### Pros and Cons
#### Pros
- Can scale different parts of an application at different rates. In a monolith, you would have to horizontally scale multiple instances of the application, even though it may only be one piece of functionality that needs more resources.
- Separate deployment. Microservices can be deployed separately from each other, and those deployments tend to be faster due to deploying a less complex application.
- Enforce modularity. The performance cost and API defined access between modules can help to enforce better decoupling, and even better team organization.
- Using different technologies. Due to the technology agnostic communication layer (HTTP, MQ, or otherwise), each microservice can be written in the most appropriate technology for its needs. In this project, I am using Spring for some microservices and Micronaut for others due to the need for me to learn both frameworks.
#### Cons
- Complexity. Deploying multiple services and supporting some communication layer between them is far more complex than deploying a monolith which communicates with itself in process.
- Speed of inter-module communication. Since communication between modules is now an I/O activity, there is a higher performance cost. This leads to a tradeoff between granularity and throughput. Think making several database queries versus calling a stored procedure. There can also be a significant performance cost simply from encoding/decoding data (serializing/deserializing JSON for instance).

### How to divide into microservices?
The first challenge of a project like this would be to decide which microservices to make. Some reading gives a few guidelines:
- Split microservices along business capabilities or domains, not technical functionality like database access, email, etc.
- Microservices should be loosely coupled. This can be tested by changing one service without that affecting others. (Change it how?)
- Microservices should be cohesive. That means that they each have a single, well-defined purpose. (How do we count purposes? What is well-defined?)
- Each microservice serves as an abstraction layer. That means that its clients should only need to understand what it does, not how.

## Design
### Source code
I am using a monorepo approach since I don't want to clutter up my Github with tons of small services. I am also the only developer and will be running the services on a single machine. Therefore, there are few benefits if any to using a polyrepo approach.

### Determining microservice boundaries
I will be following the process in the Microsoft article above.
1. Create a domain diagram. Whiteboard preferred. I do not have a whiteboard, so I did this in a notebook:

<img src="design/Domain%20Diagram.jpg" width="500">

2. Determine the bounded contexts. These are areas which share similar definitions of common items. In this application, the primary common item is beer. So I listed the qualities of beer, and then which part of the domain diagram cares about each quality. I used this to inform the decision of how to draw the bounded contexts.

<img src="design/Bounded%20Contexts.jpg" width="500">

3. Determine microservices. In a more complex application, there are steps that I have skipped here. I will simply use the bounded contexts as my microservices, as there are only 3. That gives us 3 microservices, which I have named Inventory, Serving, and Ordering. The names are simply the key domain of each context.

4. Determine technologies. Since the purpose of this project is to learn Spring and Micronaut, I want a roughly even distribution of the technologies across the microservices. As there are 3 microservices, I should use one technology for the largest microservice, and the other for the remaining 2 microservices. Micronaut is a dedicated microservice framework and Spring is a more general purpose framework. Therefore, it makes sense to use Spring for the larger microservice and Micronaut for the others. Based on the diagram, it appears that the Ordering microservice will be the largest. Therefore, I will use Spring for the Ordering microservice and Micronaut for the Serving and Inventory microservices.

This process gives us the following result:
| Microservice | Technology | Description |
| ---          | ---        | --- |
| Inventory    | Micronaut  | Manage the current tap list and stock. |
| Serving      | Micronaut  | Take orders from the customer and fulfill them from existing inventory. |
| Ordering     | Spring     | Show the tap list to the customer, allow them to make and pay for orders. |