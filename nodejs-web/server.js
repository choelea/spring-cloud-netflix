//to view es6 capabilities see http://node.green/
//node v8-options es6 module syntax currently under development (2016/06/25)
const path = require('path')
const express = require('express')
const expressHbs = require('express-handlebars')
const session = require('express-session')
const cookieParser = require('cookie-parser')
const cookieSession = require('cookie-session')
const bodyParser = require('body-parser')
const loki = require('lokijs')
const routes = require('./routes')
const Eureka = require('eureka-js-client').Eureka
const client = new Eureka()

client.start()

//setup
let database = new loki('database.loki', { autoload: true, autosave: true })
let app = express()

let passport = require('./config/passport')(client)


//settings
app.set('port', process.env.PORT || 3000)
app.set('views', path.join(__dirname, 'views'))

//view engine & main template
app.engine('.hbs', expressHbs({ defaultLayout: 'template', extname: '.hbs' }))
app.set('view engine', '.hbs')

//middleware
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: false }))
app.use(cookieParser())
app.use(cookieSession({ secret: 'secret' }));
app.use(session({
    resave: false,
    saveUninitialized: true,
    secret: 'hello'
}));

// use passport session
app.use(passport.initialize());
app.use(passport.session());

app.use('/public', express.static('public'))

//loki db reference for the router
app.use((req, res, next) => { req.database = database; next(); })

//eureka client reference for the routher
app.use((req, res, next) => { req.eurekaClient = client; next(); })

//router
routes.create(app)

// setTimeout(function() {
//     const instances = client.getInstancesByAppId('BOOKMARK-SERVICE');
//     console.log(instances);
// }, 3000);
//server
app.listen(app.get('port'), () => console.log('Listening on http://localhost:' + app.get('port')))
