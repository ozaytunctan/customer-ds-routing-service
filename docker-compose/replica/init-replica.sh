#!/bin/bash
set -e

echo "Setting up replica..."

export PGPASSWORD=replica_pass

until pg_isready -h postgres-master -p 5432
do
  echo "Waiting for master..."
  sleep 2
done

rm -rf /var/lib/postgresql/data/*

pg_basebackup -h postgres-master -p 5432 \
  -D /var/lib/postgresql/data \
  -U replicator \
  -v -P \
  --wal-method=stream

echo "primary_conninfo = 'host=postgres-master port=5432 user=replicator password=replica_pass'" \
>> /var/lib/postgresql/data/postgresql.auto.conf

touch /var/lib/postgresql/data/standby.signal