CREATE TABLE users
(
    id          serial       NOT NULL,
    username    varchar(100) not null unique,
    user_type   varchar(10)  not null,
    priv_level  integer, #this can't been non null since it is on populated for a PrivelegedUser
    abuse_level integer, #this can't be non null since its only populated for an AbusiveUser
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
ALTER TABLE users OWNER TO support2nd;

#
# You didn't tell me what relationships User has to other domain objects. In this example NONE of User subclasses
# can support relationships of their own. All relationships need to be on User class since its the only one with a table.
# can support relationships of their own. All relationships need to be on User class since its the only one with a table.
#