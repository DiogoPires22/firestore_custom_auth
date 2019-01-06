const functions = require('firebase-functions');
const admin = require('firebase-admin');


const serviceAccount = require('YOUR CERTIFICATE JSON PATH');
const urlDatabase = `https://${serviceAccount.project_id}.firebaseio.com`;


admin.initializeApp({
    credential: admin.credential.cert({
      projectId:  serviceAccount.project_id,
      clientEmail: serviceAccount.client_email,
      privateKey: serviceAccount.private_key
    }),
    databaseURL: urlDatabase
  });

exports.auth = functions.https.onRequest((request, response) => {

    const uid = "your-user-id"

    var additionalClaims = {
        premiumAccount: true
      };

    try{
        admin.auth().createCustomToken(uid, additionalClaims)
        .then(function(customToken) {
            var authInfo= {
                applicationId: serviceAccount.project_id,
                apiKey: 'YOUR API KEY', // TODO: extract it
                dataBaseUrl: urlDatabase,
                token: customToken
            }
            response.send(authInfo);

            return authInfo;
        })
    }catch{
        response.sendStatus(401)
    }
});
