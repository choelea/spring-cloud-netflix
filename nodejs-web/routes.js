
/**
 * 
 * @param {*} app 
 * @param {*} client  eureka-js-client which can fetch instance by serviceId
 */
function create(app,client) {
    app.use('/',            require('./routes/home'));

    app.use((req, res, next) => {
        res.status(404);
        res.render('404', { title: '404', message: 'This page does not exist.' });
    });
}


module.exports = {
    create: create
};
