'use strict';

/*!
 * Module dependencies.
 */
let passport = require('passport')
// const linkedin = require('./passport/linkedin');
const rp = require('request-promise');
const eurekaUtils = require('../utils/eureka-utils');
const LocalStrategy = require('passport-local').Strategy;
/**
 * Expose
 */
module.exports = function (eurekaClient) {

    // serialize sessions
    passport.serializeUser((user, cb) => cb(null, user));
    passport.deserializeUser((user, cb) => user);

    // use these strategies
    passport.use(new LocalStrategy(
        function (username, password, done) {
            var accountServiceServer = eurekaUtils.getAccountRootUrl(eurekaClient);
            // console.log(`Trying to authenticate user:${username} password:${password} account service server: ${accountServiceServer}`)
            
            rp(`${accountServiceServer}/users/${username}/password/${password}`)
                .then(function (htmlString) {
                    console.log(htmlString);
                    return done(null, JSON.parse(htmlString))
                }).catch(function (err) {
                    return done(null, false, { message: 'Incorrect username or password' })
                })
        }
    ));
    // passport.use(linkedin)
    return passport;
};
