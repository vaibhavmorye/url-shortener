# Url-shortner

## Goal of this project to write fully function url shortner with user management and payment integration.


Work :
1. Write MVP url shortening logic
2. add security
3. add some queue (ActiveMQ/kafka) mechanism to collect user behavior
4. add cache (redis) for frequently visited data / purging logic
5. add user/admin management api. 
6. add payment plan with payment gateway integration

how it will be different ?

1. we will be starting with 1 microservice and 1 small callback microservice .
2. microservice for shortening logic and microservice for call back server
3. if we needed to scaling shortening microservice we can shard based on short URL or some starting identifier .
4. still thinking............

