const passport = require('passport')
/**
 * 
 * @param {*} app 
 */
function create(app) {
    app.use('/', require('./routes/home'))
    app.get('/login', function (req, res) {
        res.render('login', {
            title: 'Login'
        });
    })
    app.post('/login',passport.authenticate('local'),function(req,res){
        res.redirect('/');
    })
    app.use('/users', require('./routes/users'))
    app.use((req, res, next) => {
        res.status(404);
        res.render('404', { title: '404', message: 'This page does not exist.' });
    });
}


module.exports = {
    create: create
};
