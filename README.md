Lien du deployement Heroku
==================


https://gestion-des-transports.herokuapp.com/

Liste des requêtes
==================

| Methode | Route                                                 | Description                                                      | Utilisateur      |
| ------- | ----------------------------------------------------- | ---------------------------------------------------------------- | ---------------- |
| POST    | /auth                                                 | Authentification                                                 | Tous             |
| GET     | /carpools/reservations?user_id                        | Récupère les reservations de covoit de l'utilisateur             | Collab & plus    |
| POST    | /carpools/reservations/:user_id/:carpool_id           | Créer une reservation de covoit                                  | Collab & plus    |
| GET     | /carpools?departureAddress=&arrivalAddress=&date=     | Afficher les covoits avec les données correspondantes            | Collab & plus    |
| GET     | /company-vehicles/reservations/:userID                | Récupère les reservations de vehicules en cours de l'utilisateur | Collab & plus    |
| POST    | /company-vehicles/reservations                        | Créer une reservation de vehicule                                | Collab & plus    |
| GET     | /company-vehicles                                     | Récupère tous les vehicules d'entreprise                         | Collab & plus    |
| POST    | /carpools                                             | Crée une nouvelle annonce                                        | Collab & plus    |
| POST    | /private-vehicles                                     | Crée un nouveau vehicule perso                                   | Collab & plus    |
| GET     | /carpools?user_id=                                    | Récupère les annonces de covoit de l'utilisateur                 | Collab & plus    |
| GET     | /company-vehicles?license=X&brand=Y                   | Récupère les véhicules de sociétés avec les filtres              | Admin            |
| POST    | /company-vehicles                                     | Créée un nouveau vehicule d'entreprise                           | Admin            |
| GET     | /company-vehicles/:vehicleID                          | Récupère les détails d'un véhicule                               | Admin            |
| GET     | /company-vehicles/coordinates                         | Récupère les coordonnées des vehicules                           | Admin            |
| PATCH   | /company-vehicles/:vehicleID                          | Modifie le statut d'un véhicule                                  | Admin            |
| PATCH   | /carpools/reservations?reservation_id=                | Annule une reservation d'un covoiturage                          | Collab & plus    |
| PATCH   | /carpools?carpool_id=                                 | Annule un covoiturage                                            | Collab & plus    |
| GET     | /users?role=driver                                    | Récupere les utilisateurs qui sont chauffeurs                    | Admin            |
| PATCH   | /users/:identification\_number                        | Change un collab en chauffeur                                    | Admin            |
| GET     | /users/:identification\_number/planning?day=X         | Récupère le planning du chauffeur pour le jour X                 | Chauffeur & plus |
| PATCH   | /company-vehicles/reservations/:reservationID         | Affecte le chauffeur à la reservation                            | Chauffeur & plus |
| GET     | /users/:identification\_number/planning?from=X&to=Y   | Récupère le taux d'occupation d'un chauffeur donné               | Chauffeur & plus |

Diagrame UML
============

![image](https://user-images.githubusercontent.com/72602777/179700968-3e64d89e-daa5-48e8-a684-c71ab1a2e9c8.png)
