//create user logged as root User
//use admin
db.createUser({
    user: "admin",
    pwd: "A123456789",
    roles: [
      { role: "dbOwner", db: "CustomersDB" }
    ]
  });

 db.customers.insertMany([
    {uuid: '9822102c-96d5-4398-aef7-8ffa5a6a5fa8', name: "Paco"},
    {uuid: '30d4af32-eb10-4548-942d-c561cae4a2dd', name: "Laura"}
 ]);