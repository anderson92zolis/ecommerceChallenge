// CREATE USER
db.createUser({
    user: "root",
    pwd: "1234567891011",
    roles: [
      { role: "dbOwner", db: "CustomersDB" }
    ]
  });

// CREATE DDBB & COLLECTION
db = new Mongo().getDB("CustomersDDBB");
db.createCollection('customers', {capped: false});

// ADD EXEMPLES
 db.customers.insertMany([
    {uuid: '9822102c-96d5-4398-aef7-8ffa5a6a5fa8', name: "Paco", pass: "pacopass", dni: "61961502P", "address": null, "ordersList": null},
    {uuid: '30d4af32-eb10-4548-942d-c561cae4a2dd', name: "Laura", pass: "laurapass", dni: "21265132E", "address": null, "ordersList": null},
    {uuid: '006080b6-e52a-475f-8ebf-1e57589a9f70', name: "Sandra", pass: "sandrapass", dni: "46321307C", "address": null, "ordersList": null},
    {uuid: '65fe54c9-48ba-4dbe-bb4f-94fbaa8d2e79', name: "Roberto", pass: "robertopass", dni: "72095340T", "address": null, "ordersList": null}
 ]);

