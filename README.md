| Methode | Route                                                 | Description                                                      | Utilisateur      |
| ------- | ----------------------------------------------------- | ---------------------------------------------------------------- | ---------------- |
| POST    | /auth                                                 | Authentification                                                 | Tous             |
| GET     | /carpools/reservations/:userID                        | Récupère les reservations de covoit en cours                     | Collab & plus    |
| GET     | /carpools/reservations/:userID?start=X&size=Y         | Récupère les reservations de covoit passés paginées              | Collab & plus    |
| POST    | /carpools/:carpoolID/reservations                     | Créer une reservation de covoit                                  | Collab & plus    |
| GET     | /carpools?depart=X&arrive=Y&date=Z                    | Afficher les covoits avec les données correspondantes            | Collab & plus    |
| GET     | /carpools/reservations/:userID                        | Afficher les reservation de covoit de l'utilisateur              | Collab & plus    |
| GET     | /company-vehicles/reservations/:userID                | Récupère les reservations de vehicules en cours de l'utilisateur | Collab & plus    |
| GET     | /company-vehicles/reservations/:userID?start=X&size=Y | Récupère les reservations de vehicules passés paginées           | Collab & plus    |
| POST    | /company-vehicles/reservations                        | Créer une reservation de vehicule                                | Collab & plus    |
| GET     | /company-vehicles                                     | Récupère tous les vehicules d'entreprise                         | Collab & plus    |
| POST    | /carpools                                             | Crée une nouvelle annonce                                        | Collab & plus    |
| POST    | /private-vehicles                                     | Crée un nouveau vehicule perso                                   | Collab & plus    |
| GET     | /carpools/:userID                                     | Récupère les annonces de covoit en cours                         | Collab & plus    |
| GET     | /carpools/:userID?start=X&size=Y                      | Récupère les annonces de covoit passés paginées                  | Collab & plus    |
| GET     | /company-vehicles?license=X&brand=Y                   | Récupère les véhicules de sociétés avec les filtres              | Admin            |
| POST    | /company-vehicles                                     | Créée un nouveau vehicule d'entreprise                           | Admin            |
| GET     | /company-vehicles/:vehicleID                          | Récupère les détails d'un véhicule                               | Admin            |
| GET     | /company-vehicles/coordinates                         | Récupère les coordonnées des vehicules                           | Admin            |
| PATCH   | /company-vehicles/:vehicleID                          | Modifie le statut d'un véhicule                                  | Admin            |
| DELETE  | /carpools/reservations/:resaID                        | Annule une reservation d'un covoiturage                          | Collab & plus    |
| PATCH   | /carpools/:carpooID                                   | Modifie le nombre de places disponibles                          | Collab & plus    |
| GET     | /users?role=driver                                    | Récupere les utilisateurs qui sont chauffeurs                    | Admin            |
| PATCH   | /users/:identification\_number                        | Change un collab en chauffeur                                    | Admin            |
| GET     | /users/:identification\_number/planning?day=X         | Récupère le planning du chauffeur pour le jour X                 | Chauffeur & plus |
| PATCH   | /company-vehicles/reservations/:reservationID         | Affecte le chauffeur à la reservation                            | Chauffeur & plus |
| GET     | /users/:identification\_number/planning?from=X&to=Y   | Récupère le taux d'occupation d'un chauffeur donné               | Chauffeur & plus |

![Image](https://user-images.githubusercontent.com/79093561/177292996-21a8f45d-1c10-481c-8412-fbf6d70211fb.png)
