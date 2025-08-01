CREATE KEYSPACE IF NOT EXISTS testkeyspace
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

USE testkeyspace;

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name TEXT,
    email TEXT
);
