const express = require('express');
const app = express();
const Eureka = require('eureka-js-client').Eureka;

app.get('/books', function (req, res) {
    var books = [];
    books.push({ bookname: 'Nodejs Web Development', author: 'David Herron' });
    books.push({ bookname: 'Mastering Web Application Development with Express ', author: 'Alexandru Vlăduțu' });
    console.log('-----------')
    res.json(books);
})
app.get('/info',function(req, res){
    res.json({name:'book service',status:'ok'});
} );
app.listen(3001, function () {
    console.log('Book Service app listening on port 3001!')
})
const client = new Eureka({
  instance: {
    instanceId: 'book-service-01',
    app: 'book-service',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    // preferIpAddress: true, // default is false and host will be used.
    // homePageUrl: 'http://localhost:3001/info',
    statusPageUrl: 'http://localhost:3001/info',
    // healthCheckUrl: 'http://localhost:3001/info',
    port: {
      '$': 3001,
      '@enabled': 'true',
    },
    vipAddress: 'book-service', // Important, otherwise spring-apigateway cannot find instance of book-service
    // secureVipAddress: 'book-service',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    fetchRegistry: false,
    host: 'localhost',
    port: 8761,
    servicePath: '/eureka/apps/'
  },
});
client.logger.level('debug');
client.start(function(error){
  console.log(error || 'complete');
});