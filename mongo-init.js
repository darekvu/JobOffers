db.createUser(
    {
        user: "goku",
        pwd: "gokuadmin",
        roles: [
            {
                role: "readWrite",
                db: "joboffers"
            }
        ]
    }
)