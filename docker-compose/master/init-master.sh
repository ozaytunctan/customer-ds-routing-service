#!/bin/bash
set -e

echo "Configuring master for replication..."

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE DATABASE customer_master_db
    WITH ENCODING 'UTF8'
    LC_COLLATE='tr_TR.UTF-8'
    LC_CTYPE='tr_TR.UTF-8'
    TEMPLATE template0;

  CREATE ROLE replicator WITH REPLICATION LOGIN PASSWORD 'replica_pass';
EOSQL

# pg_hba.conf
grep -q "replicator" "$PGDATA/pg_hba.conf" || \
echo "host replication replicator 0.0.0.0/0 md5" >> "$PGDATA/pg_hba.conf"

# replication config
echo "wal_level = replica" >> "$PGDATA/postgresql.conf"
echo "max_wal_senders = 10" >> "$PGDATA/postgresql.conf"
echo "wal_keep_size = 64MB" >> "$PGDATA/postgresql.conf"
echo "hot_standby = on" >> "$PGDATA/postgresql.conf"

# reload config
pg_ctl -D "$PGDATA" reload